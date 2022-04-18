# FUNCTION binding

If FUNCTION is an instance, there must be a reference that points to it.

## Static or Singleton

FUNCTION may be a singleton (i.e. `object`) or static. However, it is not a preferable way of "holding" FUNCTION instances in your code. You can not replace its implementation (for testing purposes, for example).  

## DATA class with extension method

A DATA class could be an excellent way to bind FUNCTION (remember: implementations i.e. instances) in one place. Something like: 

```kotlin
data class ToDo(
    val addNewTodo: (NewToDoItem) -> ToDoItem,
    val findTodoByText: (String) -> List<ToDoItem>
    //...
)
```

This data class now collects ALL verbs of the domain. You may have the default instance:

```kotlin
val toDo = ToDo(
    addNewTodo = AddNewTodo(saveToDoInRepo),
    // ...
)
```

and custom ones:

```kotlin
val toDoWithLogging = toDo.copy(
    addNewTodo = { withLogging(toDo.addNewTodo, it) }
)
```

Extension methods may become the _end_ users of the domain verbs:

```kotlin
fun ToDo.actionOne() {
    addNewTodo(NewToDoItem("Finish paperwork"))
}
with(toDoWithLogging) {
	actionOne()
}
```

> ⚡️ Pro tip: either way you do, maintain the list of verbs (bindings) in one place. Think about generating its source.
