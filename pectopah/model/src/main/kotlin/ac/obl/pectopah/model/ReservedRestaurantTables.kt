package ac.obl.pectopah.model

data class ReservedRestaurantTables(
    val reservation: Reservation,
    val tables: List<RestaurantTable>
)
