package com.daniza.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import dev.daniza.portfoliowatcher.ui.theme.TodoListTheme

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            TodoListTheme {
                Scaffold { }
            }
        }
    }
}