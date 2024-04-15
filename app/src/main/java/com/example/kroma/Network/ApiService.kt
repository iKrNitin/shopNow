package com.example.kroma.Network

import android.util.JsonReader
import android.util.Log
import com.example.kroma.Data.ChipData
import com.example.kroma.Data.Item
import com.example.kroma.Data.ProductResponse
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.DefaultHttpRequest
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.JsonConvertException
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

    /*suspend fun getProducts():ProductResponse{
        val customer: HttpResponse = client.get("https://dummyjson.com/products")
        if (customer.status.value in 200..299) {
            println("Successful response!")
            Log.d("API","Data fetched successfully")
        }
        val body:List<Item> = customer.body()
        Log.d("Api","${body.size}")
        return body
    }*/

    suspend fun getProducts(): List<Item> {
        val response: HttpResponse = client.get("https://dummyjson.com/products")
        if (response.status.value in 200..299) {
            println("Successful response!")
            Log.d("API","Data fetched successfully")
        }
        val body:ProductResponse = response.body()
        return body.products
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

