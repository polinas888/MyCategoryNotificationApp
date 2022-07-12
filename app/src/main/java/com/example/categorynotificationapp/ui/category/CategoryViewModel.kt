package com.example.categorynotificationapp.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categorynotificationapp.model.Category
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    val listLocations = listOf(Category("1", "Яблоко"), Category("2", "Груша"),
    Category("3", "Огурец"))

    fun loadLocation() {
        viewModelScope.launch {
            // get list from room
//            val loadUserLocations = locationRepository.loadUserLocations()
//            listLocationsLiveData.value = loadUserLocations
        }
    }
}