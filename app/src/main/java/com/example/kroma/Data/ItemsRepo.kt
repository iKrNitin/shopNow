package com.example.kroma.Data

import com.example.kroma.Network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.Flow
import javax.inject.Inject

class ItemsRepo
@Inject
constructor(private val apiService: ApiService){
    fun getProducts():kotlinx.coroutines.flow.Flow<List<Item>> = flow {
        emit(apiService.getProducts())
    }.flowOn(Dispatchers.IO)

    fun getCategories():kotlinx.coroutines.flow.Flow<List<String>> = flow {
        emit(apiService.getCategories())
    }.flowOn(Dispatchers.IO)
}