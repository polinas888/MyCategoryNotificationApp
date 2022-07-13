package com.example.categorynotificationapp.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.categorynotificationapp.model.Category
import com.example.categorynotificationapp.model.Notification
import com.example.categorynotificationapp.repository.category.CategoryDao

@Database(entities = [Category::class, Notification::class], version = 1)
abstract class CategoryNotificationDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}