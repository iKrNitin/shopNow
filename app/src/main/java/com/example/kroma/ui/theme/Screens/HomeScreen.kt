package com.example.kroma.ui.theme.Screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kroma.Data.ApiState
import com.example.kroma.Data.Item

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
){
    val apiState by viewModel.apiStateFlow.collectAsState()

    when (apiState) {
        is ApiState.Success<*> -> {
            val items = (apiState as ApiState.Success<List<Item>>).data
            LazyColumn {
                items(items) { item ->
                   ProductCard(item = item)
                }
            }
            Log.d("API","Success state")
        }
        is ApiState.Loading -> {
            Log.d("API","Loading state")
            // Show loading indicator
        }
        is ApiState.Failure -> {
            Log.d("API","Failure state")
            // Show error message
        }
        else -> {
            // Show empty state or handle other states
        }
    }
}

@Composable
fun ProductCard(item: Item) {
    Card(
        modifier = Modifier.padding(8.dp), // Add padding for spacing between cards
    ) {
        Column(
            modifier = Modifier.padding(16.dp) // Add padding inside the card
        ) {
            Text(text = "ID: ${item.id}")
            Text(text = "Brand: ${item.brand}")
            Text(text = "Category: ${item.category}")
            Text(text = "Description: ${item.description}")
        }
    }
}
