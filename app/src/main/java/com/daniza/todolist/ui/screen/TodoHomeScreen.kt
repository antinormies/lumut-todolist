package com.daniza.todolist.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daniza.todolist.model.DetailItemModel
import com.daniza.todolist.viewmodel.TodoViewModel
import com.daniza.todolist.ui.widget.FullProgressLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoHomeScreen(
    viewModel: TodoViewModel,
    onNavigateToDetail: (String) -> Unit,
){
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val currentLoadingState by viewModel.isLoading.collectAsStateWithLifecycle(true)
    val currentTodoListData by viewModel.todoList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadTodoListData()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Dani Zakaria") })
        }
    ) { paddingValues ->

        if(currentLoadingState){
            FullProgressLoading()
        }
        LazyColumn(state = listState) {
            itemsIndexed(items = currentTodoListData){ index, item ->
                TodoItem(
                    todo = item,
                    onClick = {
                        onNavigateToDetail(item.id.toString())
                        viewModel.clearListState()},
                    modifier = Modifier.padding(4.dp, 4.dp)
                )
            }
        }
    }
}

@Composable
fun TodoItem(
    todo: DetailItemModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (todo.completed)
                MaterialTheme.colorScheme.surfaceVariant
            else
                MaterialTheme.colorScheme.surface
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.completed,
                enabled = false,
                onCheckedChange = { }
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = todo.title,
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = if (todo.completed) TextDecoration.LineThrough else null,
                color = if (todo.completed)
                    LocalContentColor.current.copy(alpha = 0.6f)
                else
                    LocalContentColor.current
            )
        }
    }
}