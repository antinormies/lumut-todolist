package com.daniza.todolist.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface TodoDestination {
    val route: String
}

object TodoHomeDestination : TodoDestination {
    override val route: String
        get() = "home"
}

object TodoDetailDestination : TodoDestination {
    override val route: String
        get() = "detail"

    val detailIdArgs = "todoId"
    val routeWithArgs = "$route/{$detailIdArgs}"
    val arguments = listOf(
        navArgument(detailIdArgs){
            type = NavType.StringType
            defaultValue = ""
        }
    )
}