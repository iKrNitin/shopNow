package com.example.kroma.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kroma.ui.theme.Screens.HomeScreen
import com.example.kroma.ui.theme.Screens.HomeScreenDestination
import com.example.kroma.ui.theme.Screens.ProductDetailScreen
import com.example.kroma.ui.theme.Screens.ProductDetailsDestination

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier:Modifier = Modifier
){
    NavHost(navController = navController, startDestination = HomeScreenDestination.route){
        composable(route = HomeScreenDestination.route){
            HomeScreen(navigateToItemDetail = {itemId -> navController.navigate("${ProductDetailsDestination.route}/$itemId")})
        }
        
        composable(ProductDetailsDestination.route + "/{itemId}",
            arguments = listOf(navArgument("itemId"){type = NavType.IntType})
        ){
            backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")
            itemId?.let { ProductDetailScreen(itemId = it,
                navigateBack = {navController.navigateUp()}) }
        }
    }
}