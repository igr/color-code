package ac.obl.pectopah.boot.server

import ac.obl.cc.ctx.CtxD
import ac.obl.pectopah.boot.appLogger
import ac.obl.pectopah.boot.office
import ac.obl.pectopah.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

// This class should be split into multiple classes and put in a different package
// Here I use the delayed context `CtxD` just for fun - and it makes sense to be used here.

fun Application.configureRouting() {

    routing {
        post("/restaurants") {
            ((CtxD
                + call.receive<NewRestaurantInput>()
                + convertNewRestaurantInputToModel
                + office.createNewRestaurant
                + convertRestaurantToOutput
                )
                * transactional()
                )()
                .also { call.respond(it) }
        }
        get("/restaurants") {
            ((CtxD
                + office.listRestaurants
                + { it.map(convertRestaurantToOutput) }
                )
                * transactional()
                )()
                .also { call.respond(it) }

        }
        post("/reservations") {
            ((CtxD
                + call.receive<ReservationRequestInput>()
                + convertReservationRequestInputToModel
                + office.makeReservation
                )
                * transactional()
                )()
                .also { call.respond(it) }
        }

        forEachRoute {
            appLogger.info("|> $it")
        }
    }
}

// Helper method that starts transactions, without aspects.
private fun <T> transactional(): (() -> T) -> T = {
    transaction {
        addLogger(StdOutSqlLogger)
        it()
    }
}

/**
 * Iterates over all defined routes up to this point.
 */
private fun Routing.forEachRoute(routeConsumer: (Route) -> Unit) {
    val root = this
    val allRoutes = allRoutes(root)
    val allRoutesWithMethod = allRoutes.filter { it.selector is HttpMethodRouteSelector }.sortedBy { it.toString() }
    allRoutesWithMethod.forEach(routeConsumer)
}

private fun allRoutes(root: Route): List<Route> {
    return listOf(root) + root.children.flatMap { allRoutes(it) }
}

private fun Parameters.parametersToSupplier() = { name: String -> this[name].toString() }
