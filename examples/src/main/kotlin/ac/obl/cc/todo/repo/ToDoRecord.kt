package ac.obl.cc.todo.repo

import ac.obl.cc.DATA
import ac.obl.cc.todo.NewToDoItem
import ac.obl.cc.todo.ToDoItem
import ac.obl.cc.todo.TodoId
import java.time.Instant

@DATA
data class ToDoRecord(
    val id: Int?,
    val task: String,
    val createdAt: Instant?,
    val completed: Boolean = false,
    val completedAt: Instant? = null
)

fun NewToDoItem.toToDoRecord(): ToDoRecord {
    return ToDoRecord(
        id = null,
        task = this.task,
        createdAt = Instant.now()
    )
}

fun ToDoRecord.toToDoItem(): ToDoItem {
    return ToDoItem(
        id = TodoId(this.id!!),
        task = this.task,
        addedOn = this.createdAt!!,

    )
}
