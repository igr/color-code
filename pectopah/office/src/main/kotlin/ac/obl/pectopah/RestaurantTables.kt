package ac.obl.pectopah

import ac.obl.pectopah.model.RestaurantTable

fun tableHasCapacity(table: RestaurantTable, places: Int): Boolean {        // two arguments
    return table.places >= places
}
