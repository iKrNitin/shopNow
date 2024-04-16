package com.example.kroma.ui.theme.Screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kroma.Data.ApiState
import com.example.kroma.Data.ItemsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val itemsRepo: ItemsRepo):ViewModel() {

    private val _apiStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val apiStateFlow: StateFlow<ApiState> = _apiStateFlow


    fun getProductById(itemId:Int) = viewModelScope.launch {
        itemsRepo.getProductsById(itemId)
            .onStart {
                _apiStateFlow.value = ApiState.Loading
                Log.d("API","Loading state")
            }.catch {
                    e -> _apiStateFlow.value = ApiState.Failure(e)
                Log.d("API","Failure state")
            }.collect{
                    response ->
                _apiStateFlow.value = ApiState.Success(response)
                Log.d("API","Success state")
            }
    }
}