package ac.obl.pectopah.model

import java.util.*

data class RestaurantTableId(override val uuid: UUID) : Id

data class RestaurantTable(
    val id: RestaurantTableId,
    val restaurantId: RestaurantId,
    val places: Int
)

data class NewRestaurantTable(
    val restaurantId: RestaurantId,
    val places: Int
)
