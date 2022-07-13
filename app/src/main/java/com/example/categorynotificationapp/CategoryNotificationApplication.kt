package com.example.categorynotificationapp

import android.app.Application
import com.example.categorynotificationapp.repository.category.CategoryNotificationRepository

class CategoryNotificationApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CategoryNotificationRepository.initialize(this)
    }
}