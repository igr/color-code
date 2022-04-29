package ac.obl.cc.todo

import ac.obl.cc.todo.repo.toDoRepo

// Binding FUNCTION instances.

data class ToDo(
    val addNewTodo: (NewToDoItem) -> ToDoItem,
    val findTodoByText: (String) -> List<ToDoItem>
)

// default binding, used in production
val toDo = ToDo(
    addNewTodo = AddNewTodo(toDoRepo.saveToDoInRepo),
    findTodoByText = FindTodoByText
)

// enhanced binding, for debugging purposes
val toDoWithLogging = toDo.copy(
    addNewTodo = { withLogging(toDo.addNewTodo, it, "AddNewTodo") }
)

private fun <A, B> withLogging(fn: (A) -> B, value: A, name: String): B {
    println("Before $name")
    val result = fn(value)
    println("After $name")
    return result
}
