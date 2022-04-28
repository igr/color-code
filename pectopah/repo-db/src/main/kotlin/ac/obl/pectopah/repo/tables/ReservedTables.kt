package ac.obl.pectopah.repo.tables

import ac.obl.pectopah.model.Reservation
import ac.obl.pectopah.model.RestaurantTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.InsertStatement

// intermediate table
object ReservedTables : Table(name = "reserved_tables") {
    val reservationId =  uuid("reservation_id").references(Reservations.id)
    val tableId = uuid("table_id").references(Tables.id)
}
fun InsertStatement<Number>.record(reservation: Reservation, table: RestaurantTable) {
    val insert = this
    with(ReservedTables) {
        insert[reservationId] = reservation.id.uuid
        insert[tableId] = table.id.uuid
    }
}
