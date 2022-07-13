package com.example.categorynotificationapp.repository.notification

import android.content.Context
import com.example.categorynotificationapp.model.Notification

class NotificationRepository private constructor(context: Context) {

    private val notificationDao = database.notificationDao()

    suspend fun getNotifications() : List<Notification> = notificationDao.getNotifications()

    suspend fun addNotification(notification: Notification) = notificationDao.addNotification(notification)

}