package ac.obl.cc.todo

import ac.obl.cc.ACTION
import ac.obl.cc.todo.repo.toDoRepo

// Not a FUNCTION as has na implicit (hard-coded) call to another ACTION.

@ACTION
object FindTodoByText: (String) -> List<ToDoItem> {
    override fun invoke(text: String): List<ToDoItem> {
        return toDoRepo.filterToDoRecords(text)     // implicit argument, not cool
    }
}
