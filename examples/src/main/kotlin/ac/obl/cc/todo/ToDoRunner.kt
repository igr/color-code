package ac.obl.cc.todo

fun main() {
    with(toDoWithLogging) {
        actionOne()
    }
}

fun ToDo.actionOne() {
    val newTask1 = NewToDoItem("Buy coffee")
    val task1 = addNewTodo(newTask1)
    val task2 = addNewTodo(NewToDoItem("Finish paperwork"))
    println(task1)
    println(task2)
    findTodoByText("coffee")
        .forEach { println(it.task) }
}
