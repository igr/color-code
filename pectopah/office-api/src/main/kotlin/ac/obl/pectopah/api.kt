package ac.obl.pectopah

import ac.obl.pectopah.model.NewRestaurant
import ac.obl.pectopah.model.Reservation
import ac.obl.pectopah.model.ReservationRequest
import ac.obl.pectopah.model.Restaurant

// this is the business API.

data class OfficeApi(
	val makeReservation: (ReservationRequest) -> Reservation,
	val createNewRestaurant: (NewRestaurant) -> Restaurant,
	val listRestaurants: () -> List<Restaurant>
)
