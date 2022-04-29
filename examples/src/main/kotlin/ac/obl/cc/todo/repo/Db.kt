package ac.obl.cc.todo.repo

import ac.obl.cc.STATE
import java.time.Instant

/**
 * Example of a state object.
 */
@STATE
internal object Db {

    private val map = mutableMapOf<Int, ToDoRecord>()

    private fun nextId(): Int {
        return map.size + 1
    }

    @Synchronized fun save(record: ToDoRecord): ToDoRecord {
        return if (record.id == null) {
            val id = nextId()
            val recordToSave = record.copy(id = id, createdAt = Instant.now())
            map[nextId()] = recordToSave
            recordToSave
        } else {
            map[record.id] = record
            record
        }
    }

    // We minimize the number of operations you can do with the state with generic snapshot iterator.

    @Synchronized fun snapshot(filter: (ToDoRecord) -> Boolean): List<ToDoRecord> {
        return map.values.filter(filter).toList()
    }

}
