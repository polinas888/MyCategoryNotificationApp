package com.example.categorynotificationapp.repository

import android.content.Context
import androidx.room.Room

private const val DATABASE_NAME = "category-notification-database"
class DatabaseFactory private constructor(context: Context){

    val database : CategoryNotificationDatabase = Room.databaseBuilder(
        context.applicationContext,
        CategoryNotificationDatabase::class.java,
        DATABASE_NAME
    ).build()

    companion object {
        private var INSTANCE: DatabaseFactory? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = DatabaseFactory(context)
            }
        }

        fun get() : DatabaseFactory {
            return INSTANCE ?:
            throw IllegalStateException("CategoryNotificationRepository must be initialized")
        }
    }
}