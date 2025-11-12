package com.daniza.todolist.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daniza.todolist.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(
    todoId: String,
    viewModel: TodoViewModel
) {
    val todoDetail = viewModel.detailTodoList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadTodoListDetail(todoId.toIntOrNull()?:0)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Dani Zakaria") },
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            InfoRow(label = "ID", value = todoDetail.value.id.toString())
            InfoRow(label = "Title", value = todoDetail.value.title)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Completed",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.width(100.dp)
                )
                Spacer(Modifier.width(16.dp))
                Checkbox(
                    checked = todoDetail.value.completed,
                    onCheckedChange = null, // disabled
                    enabled = false
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = if (todoDetail.value.completed) "Yes" else "No",
                    color = if (todoDetail.value.completed)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.width(100.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}