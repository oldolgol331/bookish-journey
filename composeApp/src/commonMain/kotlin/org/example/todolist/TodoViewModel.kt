package org.example.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.example.todolist.db.DatabaseDriverFactory
import org.example.todolist.db.TodoDatabase
import org.example.todolist.db.TodoEntity

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
class TodoViewModel(driverFactory: DatabaseDriverFactory) : ViewModel() {

    private val database = TodoDatabase(driverFactory.createDriver())
    private val dbQuery = database.todoQueries

    val todos: StateFlow<List<TodoEntity>> = dbQuery.getAllTodos()
        .asFlow()
        .mapToList(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTodo(content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dbQuery.insertTodo(content, false)
        }
    }

    fun removeTodo(todo: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dbQuery.deleteTodo(todo.id)
        }
    }

    fun toggleTodo(todo: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dbQuery.updateTodoDone(!todo.isDone, todo.id)
        }
    }

}