package ac.obl.pectopah.model

import kotlinx.serialization.Serializable
import java.time.Duration

@Serializable
data class NewRestaurantInput(
    val name: String,
    val maxDuration: Int = 120
)

val convertNewRestaurantInputToModel: (NewRestaurantInput) -> NewRestaurant = {
    NewRestaurant(
        name = it.name,
        maxDuration = Duration.ofMinutes(it.maxDuration.toLong())
    )
}
