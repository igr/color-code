package ac.obl.pectopah.boot

import ac.obl.pectopah.boot.server.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

// This is the `boot` module, that wires everything together and runs the application.

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
        configureMonitoring()
        configureHTTP()
        configureDb()
    }.start(wait = true)
}

val appLogger: Logger = LoggerFactory.getLogger("App")
