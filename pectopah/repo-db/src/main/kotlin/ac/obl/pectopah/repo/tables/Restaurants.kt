package ac.obl.pectopah.repo.tables

import ac.obl.pectopah.model.NewRestaurant
import ac.obl.pectopah.model.Restaurant
import ac.obl.pectopah.model.RestaurantId
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.time.Duration

object Restaurants : UUIDTable(name = "restaurants") {
    val name = varchar("name", 50)
    val maxDuration = integer("max_duration")
}
fun ResultRow.toRestaurant() = Restaurant(
    id = RestaurantId(this[Restaurants.id].value),
    name = this[Restaurants.name],
    maxDuration = Duration.ofMinutes(this[Restaurants.maxDuration].toLong())
)
fun InsertStatement<Number>.record(dao: NewRestaurant) {
    val insert = this
    with(Restaurants) {
        insert[name] = dao.name
        insert[maxDuration] = dao.maxDuration.toMinutes().toInt()
    }
}
