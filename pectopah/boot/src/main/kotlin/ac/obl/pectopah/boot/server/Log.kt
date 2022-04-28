package ac.obl.pectopah.boot.server

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Defines ac.obl.pectopah.boot.server.logger.
 */
inline fun <reified T> T.logger(): Logger {
	return LoggerFactory.getLogger(T::class.java)
}

