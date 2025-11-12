package com.daniza.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.daniza.todolist.ui.navigation.TodoListNavHost
import com.daniza.todolist.ui.theme.TodoListTheme
import com.daniza.todolist.viewmodel.TodoViewModel

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: TodoViewModel by viewModels()

        setContent{
            val navController = rememberNavController()
            TodoListTheme {
                TodoListNavHost(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}