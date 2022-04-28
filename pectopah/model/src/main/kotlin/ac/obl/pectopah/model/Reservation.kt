package ac.obl.pectopah.model

import java.util.*

data class ReservationId(override val uuid: UUID) : Id

data class Reservation(
    val id: ReservationId,
    val restaurantId: RestaurantId,
    val slot: ReservationSlot,
    val persons: Int,
)
