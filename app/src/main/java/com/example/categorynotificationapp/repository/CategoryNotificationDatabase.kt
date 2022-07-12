package com.example.categorynotificationapp.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.categorynotificationapp.model.Category
import com.example.categorynotificationapp.model.Notification

@Database(entities = [Category::class, Notification::class], version = 1)
abstract class CategoryNotificationDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}