package com.example.categorynotificationapp.di

import android.content.Context
import com.example.categorynotificationapp.ui.category.CategoryFragment
import com.example.categorynotificationapp.ui.notification.NotificationFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DatabaseModule:: class, ViewModelFactoryModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

      fun inject(fragment: CategoryFragment)
      fun inject(fragment: NotificationFragment)
}