package ac.obl.pectopah.repo

import ac.obl.pectopah.model.NewRestaurant
import ac.obl.pectopah.model.Restaurant
import ac.obl.pectopah.repo.tables.Restaurants
import ac.obl.pectopah.repo.tables.record
import ac.obl.pectopah.repo.tables.toRestaurant
import org.jetbrains.exposed.sql.insert

// ACTION
class StoreNewRestaurant: (NewRestaurant) -> Restaurant {
    override fun invoke(newRestaurant: NewRestaurant): Restaurant {
        return Restaurants
            .insert { it.record(newRestaurant) }
            .result()
            .toRestaurant()
    }


}

