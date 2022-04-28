package ac.obl.pectopah

import ac.obl.pectopah.model.*
import java.time.LocalDate

class MakeReservation(
    private val fetchAllRestaurantTables: (RestaurantId) -> Sequence<RestaurantTable>,
    private val findRestaurantReservationsForDay: (RestaurantId, LocalDate) -> List<ReservedRestaurantTables>,
    private val storeReservation: (ReservationRequest, List<RestaurantTable>) -> Reservation,
) : (ReservationRequest) -> Reservation {

    override fun invoke(newReservation: ReservationRequest): Reservation {
        // first fetch
        val tables = fetchAllRestaurantTables(newReservation.restaurantId)
        val reservationsForDay = findRestaurantReservationsForDay(newReservation.restaurantId, newReservation.slot.day)

        // then process
        return tables
            .filter { tableHasCapacity(it, newReservation.places) }
            .filter { tableNotOccupied(it, newReservation.slot, reservationsForDay) }
            .map { storeReservation(newReservation, listOf(it)) }
            .firstOrNull() ?: throw IllegalStateException("Reservation not available")
    }

    // Function has 3 arguments, hence it is not a public one.
    // To become a public one, first two arguments here may be joined in the single one,
    // that has a meaning in a domain.
    private fun tableNotOccupied(
        table: RestaurantTable,
        slot: ReservationSlot,
        reservations: List<ReservedRestaurantTables>,
    ): Boolean {
        return reservations
            .filter { it.tables.contains(table) }
            .all { slotsDoNotOverlap(it.reservation.slot, slot) }
    }
}

