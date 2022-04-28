package ac.obl.pectopah.boot.server

import ac.obl.pectopah.boot.appLogger
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDb() {
    Database.connect(hikari())
}

private fun hikari(): HikariDataSource {
    val config = HikariConfig("/hikari.properties")
    if (System.getenv("DATABASE_DB") != null) {
        val databaseDb = System.getenv("DATABASE_DB")
        config.jdbcUrl = "jdbc:postgresql://$databaseDb"
    }
    if (System.getenv("DATABASE_USER") != null) {
        config.username = System.getenv("DATABASE_USER")
    }
    if (System.getenv("DATABASE_PASS") != null) {
        config.password = System.getenv("DATABASE_PASS")
    }

    config.validate()

    appLogger.info("Connecting to: ${config.jdbcUrl}")

    return HikariDataSource(config)
}
