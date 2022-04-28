package ac.obl.pectopah.repo.tables

import ac.obl.pectopah.model.RestaurantId
import ac.obl.pectopah.model.RestaurantTable
import ac.obl.pectopah.model.RestaurantTableId
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ResultRow

object Tables : UUIDTable(name = "tables") {
    val restaurantId = uuid("restaurant_id").references(Restaurants.id)
    val places = integer("places")
}
fun ResultRow.toRestaurantTable() = RestaurantTable(
    id = RestaurantTableId(this[Tables.id].value),
    restaurantId = RestaurantId(this[Tables.restaurantId]),
    places = this[Tables.places]
)
