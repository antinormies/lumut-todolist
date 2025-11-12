package com.daniza.todolist.domain

import com.daniza.todolist.model.DetailItemModel
import com.daniza.todolist.network.APIService

class ListRepository(
    private val api: APIService
) {
    suspend fun getTodos(): List<DetailItemModel> = api.getTodos()

    suspend fun getTodosDetail(id: Int): DetailItemModel = api.getTodosDetail(id)
}