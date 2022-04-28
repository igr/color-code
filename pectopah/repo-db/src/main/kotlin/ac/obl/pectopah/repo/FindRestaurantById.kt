package ac.obl.pectopah.repo

import ac.obl.pectopah.model.Restaurant
import ac.obl.pectopah.model.RestaurantId
import ac.obl.pectopah.repo.tables.Restaurants
import ac.obl.pectopah.repo.tables.toRestaurant
import org.jetbrains.exposed.sql.select

class FindRestaurantById: (RestaurantId) -> Restaurant? {
    override fun invoke(restaurantId: RestaurantId): Restaurant? {
        return Restaurants
            .select { Restaurants.id eq restaurantId }
            .map { it.toRestaurant() }
            .firstOrNull()
    }

}


