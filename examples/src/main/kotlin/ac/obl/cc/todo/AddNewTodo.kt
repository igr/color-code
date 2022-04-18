package ac.obl.cc.todo

import ac.obl.cc.FUNCTION
import ac.obl.cc.ctx.CtxD
import ac.obl.cc.todo.repo.SaveToDoInRepo

/**
 * Function, takes explicit implementation of the repo ACTION.
 */
@FUNCTION
class AddNewTodo(private val saveToDoInRepo: SaveToDoInRepo): (NewToDoItem) -> ToDoItem {
    override fun invoke(newToDoItem: NewToDoItem): ToDoItem {
        return (CtxD
            + newToDoItem
            + saveToDoInRepo    // explicit argument, cool!
            )()
    }
}
