package com.example.categorynotificationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

fun Fragment.changeFragment(args: Bundle, fragmentManager: FragmentManager) {
    this.arguments = args

    fragmentManager.commit {
        addToBackStack(null)
        replace(R.id.container, this@changeFragment)
    }
}