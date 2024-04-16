package com.example.kroma.Network

import android.util.Log
import com.example.kroma.Data.Item
import com.example.kroma.Data.ProductResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.gson.gson
import javax.inject.Inject

class ApiService @Inject constructor() {
    //First we need to create engine for making HTTP request through ktor
    //I am using android engine here

    val client = HttpClient(Android){
        engine {
            connectTimeout = 100_000
            socketTimeout = 100_000
        }

        install(ContentNegotiation){
           gson()
        }
    }

    suspend fun getProducts(): List<Item> {
        val response: HttpResponse = client.get("https://dummyjson.com/products")
        if (response.status.value in 200..299) {
            println("Successful response!")
            Log.d("API","Data fetched successfully")
        }
        val body:ProductResponse = response.body()
        return body.products
    }

    suspend fun addProduct(): Item? {
        val response: HttpResponse = client.post("https://dummyjson.com/products")
        if (response.status.value in 200..299) {
            println("Successful response!")
            Log.d("API","Data fetched successfully")
        }
        val body:Item = response.body()
        return body
    }
    suspend fun getProductById(itemId:Int): Item? {
        val response: HttpResponse = client.get("https://dummyjson.com/products/$itemId")
        if (response.status.value in 200..299) {
            println("Successful response!")
            Log.d("API","Data fetched successfully")
        }
        val body:Item = response.body()
        return body
    }

    suspend fun getCategories():List<String>{
        val response: HttpResponse = client.get("https://dummyjson.com/products/categories")
        if (response.status.value in 200..299) {
            println("Successful response!")
            Log.d("API","Data fetched successfully")
        }
        val body:List<String> = response.body()
        return body
    }
}

