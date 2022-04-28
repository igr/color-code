package ac.obl.pectopah

import ac.obl.pectopah.repo.RepoApi

// Binding done for this implementation.

val officeApi: (RepoApi) -> OfficeApi = {
    OfficeApi(
        makeReservation = MakeReservation(
            it.fetchAllRestaurantTables,
            it.findRestaurantReservationsForDay,
            it.storeReservation
        ),
        createNewRestaurant = CreateNewRestaurant(it.storeNewRestaurant),
        listRestaurants = ListAllRestaurants(it.fetchAllRestaurants)
    )
}
