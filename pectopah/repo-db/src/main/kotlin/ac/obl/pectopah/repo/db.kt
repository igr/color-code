package ac.obl.pectopah.repo

import ac.obl.pectopah.model.Id
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.util.*

// ID-related extensions

/**
 * Equals the ID of this entity to the given id.
 */
infix fun Column<UUID>.eq(id: Id): Op<Boolean> = this@eq.eq(id.uuid)
@JvmName("eqEntityID")
infix fun Column<EntityID<UUID>>.eq(id: Id): Op<Boolean> = this@eq.eq(id.uuid)

/**
 * Finds the entity with the given id.
 */
fun <E : UUIDEntity> UUIDEntityClass<E>.findById(id: Id): E? {
    return this.findById(id.uuid)
}

// Insert helpers

fun InsertStatement<*>.result(): ResultRow {
    return this.resultedValues!!.first()
}
