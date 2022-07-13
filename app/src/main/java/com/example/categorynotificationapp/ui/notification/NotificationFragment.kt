package com.example.categorynotificationapp.ui.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categorynotificationapp.MainActivity
import com.example.categorynotificationapp.R
import com.example.categorynotificationapp.appComponent
import com.example.categorynotificationapp.databinding.FragmentNotificationBinding
import com.example.categorynotificationapp.ui.category.ARG_CATEGORY_ID
import javax.inject.Inject

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    private lateinit var notificationAdapter: NotificationAdapter
    @Inject
    lateinit var notificationViewModelFactory: NotificationViewModelFactory
    private val notificationViewModel by viewModels<NotificationViewModel> {
        notificationViewModelFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        requireContext().appComponent.inject(this)
        binding.notificationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryId = arguments?.getInt(ARG_CATEGORY_ID)
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.toolbar_title_notification) + " $categoryId"
    }
}