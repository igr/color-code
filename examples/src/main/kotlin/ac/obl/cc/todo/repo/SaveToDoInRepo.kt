package ac.obl.cc.todo.repo

import ac.obl.cc.ACTION

// Uses a STATE and becomes an ACTION.

@ACTION
internal val saveToDoInRepoWithDb: SaveToDoInRepo = { newToDoItem ->
    newToDoItem
        .toToDoRecord()
        .let { Db.save(it) }
        .toToDoItem()
}

