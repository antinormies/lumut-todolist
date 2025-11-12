package com.daniza.todolist.network

import com.daniza.todolist.model.DetailItemModel
import retrofit2.http.GET

interface APIService{
    @GET("todos")
    suspend fun getTodos(): List<DetailItemModel>

    @GET("todos/{id}")
    suspend fun getTodosDetail(id: Int): DetailItemModel
}