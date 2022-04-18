package ac.obl.cc.todo

import java.time.Instant

data class TodoId(val value: Int)

data class NewToDoItem(val task: String)

data class ToDoItem(val id: TodoId, val task: String, val addedOn: Instant, val completed: Boolean = false)
