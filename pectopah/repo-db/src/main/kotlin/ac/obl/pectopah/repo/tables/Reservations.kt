package ac.obl.pectopah.repo.tables

import ac.obl.pectopah.model.*
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.time.Duration
import java.time.LocalTime

object Reservations : UUIDTable(name = "reservations") {
    val restaurantId = uuid("restaurant_id").references(Restaurants.id)
    val people = integer("people")
    val date = date("date")
    val time = integer("time")
    val duration = integer("duration")
}
fun ResultRow.toReservation() = Reservation(
    id = ReservationId(this[Reservations.id].value),
    restaurantId = RestaurantId(this[Reservations.restaurantId]),
    persons = this[Reservations.people],
    slot = ReservationSlot(
        day = this[Reservations.date],
        time = LocalTime.ofSecondOfDay(this[Reservations.time].toLong()),
        duration = Duration.ofMinutes(this[Reservations.duration].toLong())
    )
)
fun InsertStatement<Number>.record(dao: ReservationRequest) {
    val insert = this
    with(Reservations) {
        insert[restaurantId] = dao.restaurantId.uuid
        insert[people] = dao.places
        insert[date] = dao.slot.day
        insert[time] = dao.slot.time.toSecondOfDay()
        insert[duration] = dao.slot.duration.toMinutes().toInt()
    }
}
