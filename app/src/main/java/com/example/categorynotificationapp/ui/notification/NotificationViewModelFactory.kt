package com.example.categorynotificationapp.ui.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.categorynotificationapp.repository.notification.NotificationRepository
import com.example.categorynotificationapp.ui.category.CategoryViewModel
import javax.inject.Inject

class NotificationViewModelFactory @Inject constructor(
    val notificationRepository: NotificationRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java))
            return NotificationViewModel(notificationRepository) as T
        else
            throw IllegalArgumentException("Unable to construct viewModel")
    }
}