package com.example.categorynotificationapp.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult
import com.example.categorynotificationapp.databinding.FragmentNotificationCreateOrChangeBinding
import com.example.categorynotificationapp.model.Notification
import com.google.gson.GsonBuilder

const val IS_NEW_NOTIFICATION = "isNewNotification"
class NotificationCreateOrChangeFragment : Fragment() {
    private lateinit var binding: FragmentNotificationCreateOrChangeBinding
    private var isNewNotification = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationCreateOrChangeBinding.inflate(layoutInflater)
        val gson = GsonBuilder().create()
        val notificationArgs = gson.fromJson(arguments?.getString(ARG_NOTIFICATION), Notification::class.java)
        if (notificationArgs != null) {
            binding.createOrEditNotificationEditText.setText(notificationArgs.text)
            isNewNotification = false
        }
        binding.cancelButton.setOnClickListener {
            val manager: FragmentManager = requireActivity().supportFragmentManager
            val trans: FragmentTransaction = manager.beginTransaction()
            trans.remove(this)
            trans.commit()
            manager.popBackStack()
        }

        binding.okButton.setOnClickListener {
            val notification = binding.createOrEditNotificationEditText.text.toString()
            setFragmentResult(NOTIFICATION_REQUEST_KEY, bundleOf(ARG_NOTIFICATION to notification,
                IS_NEW_NOTIFICATION to isNewNotification))
            val manager: FragmentManager = requireActivity().supportFragmentManager
            val trans: FragmentTransaction = manager.beginTransaction()
            trans.remove(this)
            trans.commit()
            manager.popBackStack()
        }
        return binding.root
    }
}