package com.daniza.todolist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.daniza.todolist.ui.screen.TodoDetailScreen
import com.daniza.todolist.ui.screen.TodoHomeScreen
import com.daniza.todolist.viewmodel.TodoViewModel

@Composable
fun TodoListNavHost(
    navController: NavHostController,
    viewModel: TodoViewModel
) {
    NavHost(
        navController = navController,
        startDestination = TodoHomeDestination.route
    ){
        composable(route = TodoHomeDestination.route){
            TodoHomeScreen(
                viewModel = viewModel,
                onNavigateToDetail = { todoId ->
                navController.navigate("${TodoDetailDestination.route}/$todoId") {
                    launchSingleTop = true
                }
            })
        }

        composable(
            route = TodoDetailDestination.routeWithArgs,
            arguments = TodoDetailDestination.arguments
        ) { navBackStackEntry: NavBackStackEntry ->
            val  todoId = navBackStackEntry.arguments?.getString(TodoDetailDestination.detailIdArgs)
            TodoDetailScreen(
                todoId.orEmpty(),
                viewModel
            )
        }
    }
}