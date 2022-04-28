package ac.obl.pectopah

import ac.obl.pectopah.model.Restaurant

class ListAllRestaurants(
    private val fetchAllRestaurants: () -> Sequence<Restaurant>
): () -> List<Restaurant> {

    override fun invoke(): List<Restaurant> {
        return fetchAllRestaurants().toList()
    }
}
