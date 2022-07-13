package com.example.categorynotificationapp.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.categorynotificationapp.repository.category.CategoryRepository
import javax.inject.Inject

class CategoryViewModelFactory @Inject constructor(
    val categoryRepository: CategoryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java))
            return CategoryViewModel(categoryRepository) as T
        else
            throw IllegalArgumentException("Unable to construct viewModel")
    }
}