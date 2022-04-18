package ac.obl.cc.todo.repo

import ac.obl.cc.todo.NewToDoItem
import ac.obl.cc.todo.ToDoItem

typealias SaveToDoInRepo = (NewToDoItem) -> ToDoItem
typealias FilterToDoRecords = (String) -> List<ToDoItem>

// Abstract barrier for the ACTIONs.
// ACTIONs are internal to prevent leaking.

data class ToDoRepo(
    val saveToDoInRepo: SaveToDoInRepo = saveToDoInRepoWithDb,
    val filterToDoRecords: FilterToDoRecords = filterToDoRecordsWithDb
)

val toDoRepo = ToDoRepo()
