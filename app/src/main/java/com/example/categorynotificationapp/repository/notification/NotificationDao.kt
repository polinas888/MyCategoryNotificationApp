package com.example.categorynotificationapp.repository.notification

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.categorynotificationapp.model.Notification

@Dao
interface NotificationDao {
    @Query("SELECT * FROM notification")
    suspend fun getNotifications(): List<Notification>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNotification(notification: Notification)
}