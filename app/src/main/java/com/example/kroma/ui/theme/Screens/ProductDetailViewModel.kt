package com.example.kroma.ui.theme.Screens

import androidx.lifecycle.ViewModel
import com.example.kroma.Data.ItemsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val itemsRepo: ItemsRepo):ViewModel() {
}