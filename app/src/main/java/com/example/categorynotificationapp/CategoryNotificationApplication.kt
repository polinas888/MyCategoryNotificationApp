package com.example.categorynotificationapp

import android.app.Application
import android.content.Context
import com.example.categorynotificationapp.di.AppComponent
import com.example.categorynotificationapp.di.DaggerAppComponent
import com.example.categorynotificationapp.repository.DatabaseFactory

class CategoryNotificationApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
        DatabaseFactory.initialize(this)
    }
}

val Context.appComponent: AppComponent
    get() = when(this) {
        is CategoryNotificationApplication -> appComponent
        else -> this.applicationContext.appComponent
}