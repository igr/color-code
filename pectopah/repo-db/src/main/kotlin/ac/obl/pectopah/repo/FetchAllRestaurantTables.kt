package ac.obl.pectopah.repo

import ac.obl.pectopah.model.RestaurantId
import ac.obl.pectopah.model.RestaurantTable
import ac.obl.pectopah.repo.tables.Tables
import ac.obl.pectopah.repo.tables.toRestaurantTable
import org.jetbrains.exposed.sql.select

class FetchAllRestaurantTables: (RestaurantId) -> Sequence<RestaurantTable> {
    override fun invoke(restaurantId: RestaurantId): Sequence<RestaurantTable> {
        return Tables
            .select { Tables.restaurantId eq restaurantId.uuid }
            .map { it.toRestaurantTable() }
            .asSequence()
    }
}
