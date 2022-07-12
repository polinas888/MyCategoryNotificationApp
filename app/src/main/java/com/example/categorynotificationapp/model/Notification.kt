package com.example.categorynotificationapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notification(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var text: String
)