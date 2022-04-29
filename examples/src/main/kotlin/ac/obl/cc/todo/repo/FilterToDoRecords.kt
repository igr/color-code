package ac.obl.cc.todo.repo

import ac.obl.cc.ACTION

// Uses a STATE and becomes an ACTION.

@ACTION
internal val filterToDoRecordsWithDb: FilterToDoRecords = { text ->
    Db.snapshot { it.task.contains(text) }
        .map { it.toToDoItem() }
        .toList()
}
