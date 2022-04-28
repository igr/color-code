@file:JvmName("RestaurantSerializerKt")

package ac.obl.pectopah.model

import kotlinx.serialization.Serializable

@Serializable
data class RestaurantOutput(
    val id: String,
    val name: String,
    val maxDuration: Int = 120
)

val convertRestaurantToOutput: (Restaurant) -> RestaurantOutput = {
    RestaurantOutput(
        id = it.id.uuid.toString(),
        name = it.name,
        maxDuration = it.maxDuration.toMinutes().toInt()
    )
}
