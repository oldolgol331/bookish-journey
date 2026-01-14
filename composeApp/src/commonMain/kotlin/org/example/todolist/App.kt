package org.example.todolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.todolist.db.DatabaseDriverFactory
import org.example.todolist.db.TodoEntity
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(driverFactory: DatabaseDriverFactory) {
    MaterialTheme {
        val viewModel = viewModel { TodoViewModel(driverFactory) }
        val todos by viewModel.todos.collectAsState()
        TodoScreen(
            todos = todos,
            onAddTodo = { viewModel.addTodo(it) },
            onRemoveTodo = { viewModel.removeTodo(it) },
            onToggleTodo = { viewModel.toggleTodo(it) }
        )
    }
}

@Composable
fun TodoScreen(
    todos: List<TodoEntity>,
    onAddTodo: (String) -> Unit,
    onRemoveTodo: (TodoEntity) -> Unit,
    onToggleTodo: (TodoEntity) -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f),
                label = { Text("할 일을 입력하세요") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    onAddTodo(inputText)
                    inputText = ""
                }
            ) {
                Text("추가")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(todos) { todo ->
                TodoItem(
                    todo = todo,
                    onClick = { onToggleTodo(todo) },
                    onDelete = { onRemoveTodo(todo) }
                )
            }
        }
    }
}

@Composable
fun TodoItem(
    todo: TodoEntity,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = todo.content,
                style = if (todo.isDone)
                    MaterialTheme.typography.bodyLarge.copy(
                        textDecoration = TextDecoration.LineThrough,
                        color = Color.Gray
                    )
                else
                    MaterialTheme.typography.bodyLarge
            )
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "삭제")
            }
        }
    }
}