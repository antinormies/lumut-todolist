package com.daniza.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniza.todolist.domain.ListRepository
import com.daniza.todolist.model.DetailItemModel
import com.daniza.todolist.network.APIClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel : ViewModel() {
    private val apiClient = APIClient.apiService
    private val repository: ListRepository = ListRepository(apiClient)
    private val _isLoading : MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isLoading: SharedFlow<Boolean> get() = _isLoading

    private val _todoList : MutableStateFlow<List<DetailItemModel>> = MutableStateFlow(emptyList())
    val todoList: StateFlow<List<DetailItemModel>> get() =  _todoList

    private val _detailTodoList: MutableStateFlow<DetailItemModel> = MutableStateFlow(
        DetailItemModel(0,0,"-",false)
    )
    val detailTodoList: StateFlow<DetailItemModel> get()= _detailTodoList

    fun loadTodoListData() {
        viewModelScope.launch {
            _isLoading.emit(true)
            val todoListData = withContext(Dispatchers.IO) { repository.getTodos() }
            _todoList.emit(todoListData)
            _isLoading.emit(false)
        }
    }

    fun loadTodoListDetail(todoId: Int) {
        viewModelScope.launch {
            _isLoading.emit(true)
            val detailItemData = withContext(Dispatchers.IO){ repository.getTodosDetail(todoId) }
            _detailTodoList.emit(detailItemData)
            _isLoading.emit(false)
        }
    }
}