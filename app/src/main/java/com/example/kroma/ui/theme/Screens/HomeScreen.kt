package com.example.kroma.ui.theme.Screens

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.kroma.Data.ApiState
import com.example.kroma.Data.ChipData
import com.example.kroma.Data.Item
import com.example.kroma.ui.theme.KromaTheme
import java.util.Locale.Category

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navigateToItemDetail:(Int) -> Unit
) {
    val apiState by viewModel.apiStateFlow.collectAsState()
    Scaffold(
        topBar = {
           Card(elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
               colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimaryContainer)) {
           TopAppBar(
           title = { Text(text = "Discover",
               style = MaterialTheme.typography.titleLarge) },
           actions = { androidx.compose.material3.Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null,
               modifier = Modifier.padding(end = 10.dp))})}
           }
    ) {innerpadding ->
        Column(modifier = Modifier.padding(innerpadding)) {
        /*when (apiState) {
        is ApiState.Success<*> -> {
            val categories = (apiState as ApiState.Success<List<String>>).data
            CatergoryList(chips = categories)
            Log.d("API","Success state")
        }
        is ApiState.Loading -> {
            CircularProgressIndicator(color = Color.Black, modifier = Modifier.size(2.dp))
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
    }*/

        when (apiState) {
            is ApiState.Success<*> -> {
                val items = (apiState as ApiState.Success<List<Item>>).data
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    items(items){
                        item -> ProductCard(item = item)
                    }
                }
                Log.d("API","Success state")
            }
            is ApiState.Loading -> {
                CircularProgressIndicator(color = Color.Black, modifier = Modifier.size(20.dp))
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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatergoryList(chips:List<String>,
                  modifier: Modifier = Modifier){
        LazyRow {
            items(chips) { chipData ->
                var selected by remember { mutableStateOf(false) }
                FilterChip(
                    onClick = { selected = !selected },
                    label = { Text(chipData) },
                    selected = selected,
                    modifier = Modifier.padding(2.dp),
                )
            }
        }
}

@Composable
fun ProductCard(item:Item) {
    Card(
        modifier = Modifier.padding(8.dp), // Add padding for spacing between cards
    ) {
            Card(elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)) {
                AsyncImage(model = item.thumbnail,
                    contentDescription = null,)

                Text(text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 3.dp),
                    overflow = TextOverflow.Clip)

           Row {
                Text(text = item.price.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 3.dp))
               Text(text = item.rating.toString(),
                   style = MaterialTheme.typography.titleMedium,
                   modifier = Modifier.padding(start = 3.dp))
            }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    KromaTheme {

    }
}
