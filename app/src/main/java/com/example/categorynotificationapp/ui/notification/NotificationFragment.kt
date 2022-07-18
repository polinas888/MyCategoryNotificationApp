package com.example.categorynotificationapp.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categorynotificationapp.MainActivity
import com.example.categorynotificationapp.R
import com.example.categorynotificationapp.appComponent
import com.example.categorynotificationapp.changeFragment
import com.example.categorynotificationapp.databinding.FragmentNotificationBinding
import com.example.categorynotificationapp.model.Notification
import com.example.categorynotificationapp.ui.category.ARG_CATEGORY_ID
import kotlinx.coroutines.launch
import javax.inject.Inject

const val ARG_NOTIFICATION = "arg_notification"
const val NOTIFICATION_REQUEST_KEY = "requestKey"

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    private lateinit var notificationAdapter: NotificationAdapter
    private var categoryId = 0

    @Inject
    lateinit var notificationViewModelFactory: NotificationViewModelFactory
    private val notificationViewModel by viewModels<NotificationViewModel> {
        notificationViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(NOTIFICATION_REQUEST_KEY) { requestKey, bundle ->
            val notification = bundle.getString(ARG_NOTIFICATION)
            val newNotification = notification?.let { notificationText ->
                categoryId.let { category_id ->
                    Notification(text = notificationText, category_id = category_id)
                }
            }
            lifecycleScope.launch {
                if (newNotification != null) {
                    notificationViewModel.saveNotification(newNotification)
                }
                binding.emptyListText.visibility = View.INVISIBLE
                notificationViewModel.loadData()
                notificationViewModel.notificationListLiveData.value?.let { notifications ->
                    updateUI(notifications)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        requireContext().appComponent.inject(this)
        binding.notificationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryId = arguments?.getInt(ARG_CATEGORY_ID)!!
        notificationViewModel.categoryId.value = categoryId
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.toolbar_title_notification) + " $categoryId"

        notificationViewModel.loadData()
        notificationViewModel.notificationListLiveData.observe(
            viewLifecycleOwner,
            Observer { notifications ->
                if (notifications.isEmpty()) {
                    binding.emptyListText.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                } else {
                    updateUI(notifications)
                    binding.progressBar.visibility = View.GONE
                    binding.emptyListText.visibility = View.INVISIBLE
                }
            })

        binding.addButton.setOnClickListener {
            val fragment = NotificationCreateOrChangeFragment()
            val args = Bundle()
            fragment.changeFragment(args, parentFragmentManager)
        }
    }

    private fun updateUI(notifications: List<Notification>) {
        notificationAdapter =
            NotificationAdapter((notifications),
                { notification -> deleteNotification(notification) })
        binding.notificationRecyclerView.adapter = notificationAdapter
    }

    private fun deleteNotification(notification: Notification) {
        lifecycleScope.launch {
            notificationViewModel.deleteNotification(notification)
            notificationViewModel.loadData()
            notificationViewModel.notificationListLiveData.observe(viewLifecycleOwner, Observer {
                updateUI(it)
                if (notificationViewModel.notificationListLiveData.value?.isNotEmpty() == true) {
                    binding.emptyListText.visibility = View.INVISIBLE
                } else {
                    binding.emptyListText.visibility = View.VISIBLE
                }
            })
        }
    }
}


