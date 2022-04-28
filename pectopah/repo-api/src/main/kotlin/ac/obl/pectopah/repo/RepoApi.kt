package ac.obl.pectopah.repo

import ac.obl.pectopah.model.*
import java.time.LocalDate

data class RepoApi(
    val storeNewRestaurant: (NewRestaurant) -> Restaurant,
    val fetchAllRestaurants: () -> Sequence<Restaurant>,
    val fetchAllRestaurantTables: (RestaurantId) -> Sequence<RestaurantTable>,
    val findRestaurantById: (RestaurantId) -> Restaurant?,
    val findRestaurantReservationsForDay: (RestaurantId, LocalDate) -> List<ReservedRestaurantTables>,
    val storeReservation: (ReservationRequest, List<RestaurantTable>) -> Reservation,
)
