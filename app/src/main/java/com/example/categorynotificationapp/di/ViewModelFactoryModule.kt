package com.example.categorynotificationapp.di

import com.example.categorynotificationapp.repository.category.CategoryRepository
import com.example.categorynotificationapp.repository.notification.NotificationRepository
import com.example.categorynotificationapp.ui.category.CategoryViewModelFactory
import com.example.categorynotificationapp.ui.notification.NotificationViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule {

    @Provides
    fun provideCategoryViewModelFactory(categoryRepository: CategoryRepository): CategoryViewModelFactory {
        return CategoryViewModelFactory(
            categoryRepository = categoryRepository)
    }

    @Provides
    fun provideNotificationViewModelFactory(notificationRepository: NotificationRepository): NotificationViewModelFactory {
        return NotificationViewModelFactory(
            notificationRepository = notificationRepository)
    }
}