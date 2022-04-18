package ac.obl.cc.todo.repo

import ac.obl.cc.ACTION
import ac.obl.cc.ctx.Ctx
import ac.obl.cc.todo.ToDoItem

@ACTION
internal val filterToDoRecordsWithDb: FilterToDoRecords = { text ->
    (Ctx
        + text
        + { { todoRecord: ToDoRecord -> todoRecord.task.contains(it)} }
        + { Db.snapshot(it) }   // uses side-effect
        + { map(it) }
        )()
}

private fun map(list: List<ToDoRecord>): List<ToDoItem> = list.map { it.toToDoItem() }.toList()

