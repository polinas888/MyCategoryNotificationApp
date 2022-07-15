package com.example.categorynotificationapp.repository.notification

import com.example.categorynotificationapp.model.Notification
import javax.inject.Inject

class NotificationRepository @Inject constructor(notificationDao: NotificationDao): NotificationDao {

    private val notificationDao: NotificationDao by lazy {
        notificationDao
    }

    override suspend fun getNotifications(categoryId: Int): List<Notification> {
        return notificationDao.getNotifications(categoryId)
    }

    override suspend fun addNotification(notification: Notification) {
        notificationDao.addNotification(notification)
    }

}