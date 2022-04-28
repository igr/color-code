package ac.obl.pectopah.repo

val repoApiDb: () -> RepoApi = {
    RepoApi(
        storeNewRestaurant = StoreNewRestaurant(),
        fetchAllRestaurants = FetchAllRestaurants(),
        fetchAllRestaurantTables = FetchAllRestaurantTables(),
        findRestaurantById = FindRestaurantById(),
        findRestaurantReservationsForDay = FindRestaurantReservationsForDay(),
        storeReservation = StoreReservation(),
    )
}
