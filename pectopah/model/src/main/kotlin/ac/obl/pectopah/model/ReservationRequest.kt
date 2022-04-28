package ac.obl.pectopah.model

data class ReservationRequest(
    val restaurantId: RestaurantId,
    val slot: ReservationSlot,
    val places: Int,
)
