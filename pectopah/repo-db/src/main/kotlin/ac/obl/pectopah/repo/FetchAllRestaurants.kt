package ac.obl.pectopah.repo

import ac.obl.pectopah.model.Restaurant
import ac.obl.pectopah.repo.tables.Restaurants
import ac.obl.pectopah.repo.tables.Restaurants.name
import ac.obl.pectopah.repo.tables.toRestaurant
import org.jetbrains.exposed.sql.selectAll

class FetchAllRestaurants: () -> Sequence<Restaurant> {
    override fun invoke(): Sequence<Restaurant> {
        return Restaurants
            .selectAll()
            .orderBy(name)
            .map { it.toRestaurant() }
            .asSequence()
    }
}
