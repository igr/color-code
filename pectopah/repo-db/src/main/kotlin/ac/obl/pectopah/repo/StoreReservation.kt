package ac.obl.pectopah.repo

import ac.obl.pectopah.model.Reservation
import ac.obl.pectopah.model.ReservationRequest
import ac.obl.pectopah.model.RestaurantTable
import ac.obl.pectopah.repo.tables.Reservations
import ac.obl.pectopah.repo.tables.ReservedTables
import ac.obl.pectopah.repo.tables.record
import ac.obl.pectopah.repo.tables.toReservation
import org.jetbrains.exposed.sql.insert

class StoreReservation: (ReservationRequest, List<RestaurantTable>) -> Reservation {
    override fun invoke(reservationRequest: ReservationRequest, tables: List<RestaurantTable>): Reservation {

        val reservation = Reservations
                .insert { it.record(reservationRequest) }
                .result()
                .toReservation()

        tables.forEach { table ->
            ReservedTables.insert { it.record(reservation, table) }
        }

        return reservation
    }
}
