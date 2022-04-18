package ac.obl.cc.todo.repo

import ac.obl.cc.ACTION
import ac.obl.cc.ctx.Ctx

@ACTION
internal val saveToDoInRepoWithDb: SaveToDoInRepo = { newToDoItem ->
    (Ctx
        + newToDoItem
        + { it.toToDoRecord() }
        + { Db.save(it) }           // state as implicit argument
        + { it.toToDoItem() }
        )()
}

