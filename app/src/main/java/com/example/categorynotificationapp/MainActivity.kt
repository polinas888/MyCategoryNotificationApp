package com.example.categorynotificationapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.categorynotificationapp.ui.category.CategoryFragment
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, CategoryFragment()).commit()
        }
    }
}