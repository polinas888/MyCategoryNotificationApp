package com.example.categorynotificationapp.ui.notification

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categorynotificationapp.model.Notification
import com.example.categorynotificationapp.repository.DataResult
import com.example.categorynotificationapp.repository.notification.NotificationRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationViewModel @Inject constructor(private val notificationRepository: NotificationRepository) :
    ViewModel() {
    val notificationListLiveData = MutableLiveData<List<Notification>>()
    val categoryId = MutableLiveData(0)

    suspend fun saveNotification(notification: Notification) {
        try {
            notificationRepository.addNotification(notification)
        } catch (exception: SQLiteConstraintException) {
           Log.i("NotificationLog", "Couldn't save notification")
        }
    }

    suspend fun deleteNotification(notification: Notification) {
        try {
            notificationRepository.deleteNotification(notification)
        } catch (e: java.lang.Exception) {
            Log.i("NotificationLog", "Couldn't delete notification")
        }
    }

    fun loadData() {
        viewModelScope.launch {
            when (val notification = categoryId.value?.let { getNotifications(it) }) {
                is DataResult.Ok -> {
                    notificationListLiveData.value = notification.response!!
                }
                is DataResult.Error -> notification.error
            }
        }
    }

    private suspend fun getNotifications(categoryId: Int): DataResult<List<Notification>> {
        return try {
            val notifications = notificationRepository.getNotifications(categoryId)
            DataResult.Ok(notifications)
        } catch (e: Exception) {
            DataResult.Error(e.message.toString())
        }
    }
}