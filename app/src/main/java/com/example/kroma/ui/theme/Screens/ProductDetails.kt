package com.example.kroma.ui.theme.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.kroma.Data.Item

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel()
){

}

@Composable
fun ProductDetailLayout(){

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

}