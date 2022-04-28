package ac.obl.pectopah.repo

import ac.obl.pectopah.model.Reservation
import ac.obl.pectopah.model.ReservedRestaurantTables
import ac.obl.pectopah.model.RestaurantId
import ac.obl.pectopah.model.RestaurantTable
import ac.obl.pectopah.repo.tables.*
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import java.time.LocalDate

class FindRestaurantReservationsForDay: (RestaurantId, LocalDate) -> List<ReservedRestaurantTables> {
    override fun invoke(restaurantId: RestaurantId, localDate: LocalDate): List<ReservedRestaurantTables> {
        return Reservations
            .select {
                (Reservations.restaurantId eq restaurantId) and (Reservations.date eq localDate)
            }
            .map { it.toReservation() }
            .map {
                ReservedRestaurantTables(it, reservedTables(it))
            }
    }

    private fun reservedTables(reservation: Reservation): List<RestaurantTable> {
        return (Reservations innerJoin ReservedTables innerJoin Tables)
            .select { Reservations.id eq reservation.id}
            .map { it.toRestaurantTable() }
            .toList()
    }
}
