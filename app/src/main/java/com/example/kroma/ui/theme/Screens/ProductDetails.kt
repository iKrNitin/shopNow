package com.example.kroma.ui.theme.Screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.kroma.Data.ApiState
import com.example.kroma.Data.Item
import com.example.kroma.Navigation.NavigationDestination

object ProductDetailsDestination: NavigationDestination {
    override val route: String
        get() = "Product_Details"
}

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    navigateBack:() -> Unit,
    itemId:Int
){
    LaunchedEffect(itemId){
        viewModel.getProductById(itemId)
    }
    val apiState by viewModel.apiStateFlow.collectAsState()
    when (apiState) {
        is ApiState.Success<*> -> {
            val details = (apiState as ApiState.Success<Item>).data
            ProductDetailLayout(item = details)
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

@Composable
fun ProductDetailLayout(item: Item){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ImageSection(photos = item.images)
        ProductDetailSection(item = item)
    }
}

@Composable
fun ImageSection(
    photos:List<String>,
    modifier: Modifier = Modifier
){
    LazyRow(contentPadding = PaddingValues(start = 4.dp, end = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)){
        items(photos){
                photoUrl ->
            PhotoItem(photoUrl = photoUrl)
        }
    }
}

@Composable
fun PhotoItem(photoUrl:String){
    Card(modifier = Modifier.padding(top = 10.dp)
    ){
        /*Image(painter = painterResource(id = R.drawable.loading), contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentScale = ContentScale.Inside)*/
        AsyncImage(
            model = photoUrl ,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Inside)
    }
}
@Composable
fun ProductDetailSection(item:Item){
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)){
        Column {
            Text(text = item.title,
                style = MaterialTheme.typography.titleMedium)
            Text(text = item.brand)
            Text(text = item.category)
            Text(text = item.description)
        }
    }
}