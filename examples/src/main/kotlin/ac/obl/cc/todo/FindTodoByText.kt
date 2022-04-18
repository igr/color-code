package ac.obl.cc.todo

import ac.obl.cc.FUNCTION
import ac.obl.cc.ctx.CtxD
import ac.obl.cc.todo.repo.toDoRepo

@FUNCTION
object FindTodoByText: (String) -> List<ToDoItem> {
    override fun invoke(text: String): List<ToDoItem> {
        return (CtxD
            + text
            + toDoRepo.filterToDoRecords     // implicit argument, not OK for testing
            )()
    }
}
