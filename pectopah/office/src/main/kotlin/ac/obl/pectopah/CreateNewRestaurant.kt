package ac.obl.pectopah

import ac.obl.pectopah.model.NewRestaurant
import ac.obl.pectopah.model.Restaurant

class CreateNewRestaurant(private val storeNewRestaurant: (NewRestaurant) -> Restaurant): (NewRestaurant) -> Restaurant {

    override fun invoke(newRestaurant: NewRestaurant): Restaurant {
        return storeNewRestaurant(newRestaurant)
    }
}
