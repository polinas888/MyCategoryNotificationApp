package com.example.categorynotificationapp.di

import com.example.categorynotificationapp.repository.DatabaseFactory
import com.example.categorynotificationapp.repository.category.CategoryDao
import com.example.categorynotificationapp.repository.category.CategoryRepository
import com.example.categorynotificationapp.repository.notification.NotificationDao
import com.example.categorynotificationapp.repository.notification.NotificationRepository
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {
    @Provides
    fun provideCategoryDao(): CategoryDao {
        return DatabaseFactory.get().database.categoryDao()
    }

    @Provides
    fun provideNotificationDao(): NotificationDao {
        return DatabaseFactory.get().database.notificationDao()
    }

    @Provides
    fun provideCategoryRepository(categoryDao: CategoryDao): CategoryRepository {
        return CategoryRepository(
            categoryDao = categoryDao
        )
    }

    @Provides
    fun provideNotificationRepository(notificationDao: NotificationDao): NotificationRepository {
        return NotificationRepository(
            notificationDao = notificationDao
        )
    }
}