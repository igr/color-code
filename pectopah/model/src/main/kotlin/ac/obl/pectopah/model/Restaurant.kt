package ac.obl.pectopah.model

import java.time.Duration
import java.util.*

data class RestaurantId(override val uuid: UUID) : Id

data class Restaurant(
    val id: RestaurantId,
    val name: String,
    val maxDuration: Duration,
)

data class NewRestaurant(
    val name: String,
    val maxDuration: Duration,
)
