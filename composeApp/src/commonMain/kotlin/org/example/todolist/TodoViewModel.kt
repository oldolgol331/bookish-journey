package org.example.todolist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * PackageName : org.example.todolist
 * FileName    : TodoViewModel
 * Author      : oldolgol331
 * Date        : 26. 1. 14.
 * Description :
 * =====================================================================================================================
 * DATE          AUTHOR               DESCRIPTION
 * ---------------------------------------------------------------------------------------------------------------------
 * 26. 1. 14.    oldolgol331          Initial creation
 */
class TodoViewModel : ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())

    val todos: StateFlow<List<Todo>> = _todos.asStateFlow()

    private var nextId: Long = 1

    fun addTodo(content: String) {
        if (content.isBlank()) return

        val newTodo = Todo(id = nextId++, content = content)

        _todos.update { currentList -> currentList + newTodo }
    }

    fun removeTodo(todo: Todo) {
        _todos.update { currentList -> currentList.filter { it.id != todo.id } }
    }

    fun toggleTodo(todo: Todo) {
        _todos.update { currentList -> currentList.map { if (it.id == todo.id) it.copy(isDone = !it.isDone) else it } }
    }
}