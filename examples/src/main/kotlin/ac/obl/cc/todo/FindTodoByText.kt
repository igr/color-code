package ac.obl.cc.todo

import ac.obl.cc.todo.repo.toDoRepo

object FindTodoByText: (String) -> List<ToDoItem> {
    override fun invoke(text: String): List<ToDoItem> {
        return toDoRepo.filterToDoRecords(text)     // implicit argument, not cool
    }
}
