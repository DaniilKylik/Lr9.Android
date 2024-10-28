package com.example.lr9.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lr9.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    task: Task,
    onToggleCompletion: () -> Unit,
    onDelete: () -> Unit,
    onToggleDetails: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Назва завдання та checkbox для зміни статусу виконання
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = task.title)
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { onToggleCompletion() }
                )
            }

            // Анімоване відображення деталей завдання
            AnimatedVisibility(visible = task.showDetails) {
                Column {
                    Text(text = task.description)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            // Кнопки для показу деталей та видалення завдання
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onToggleDetails) {
                    Text(text = if (task.showDetails) "Hide Details" else "Show Details")
                }
                Button(onClick = onDelete) {
                    Text(text = "Delete")
                }
            }
        }
    }
}