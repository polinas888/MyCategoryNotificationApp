package com.example.categorynotificationapp.ui.notification

import android.os.Bundle
import android.util.Log
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

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    private lateinit var notificationAdapter: NotificationAdapter

    @Inject
    lateinit var notificationViewModelFactory: NotificationViewModelFactory
    private val notificationViewModel by viewModels<NotificationViewModel> {
        notificationViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val notification = bundle.getString("bundleKey")
            val categoryId = arguments?.getInt(ARG_CATEGORY_ID)
            Log.i("NotificationLog", notificationViewModel.categoryId.value.toString())
            val newNotification = notification?.let { notificationText ->
                categoryId?.let { category_id ->
                    Notification(text = notificationText, category_id = category_id)
                }
            }
            Log.i("notificationLog", notification.toString())
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
        val categoryId = arguments?.getInt(ARG_CATEGORY_ID)!!
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
                }
            })

        binding.addButton.setOnClickListener {
            val fragment = NotificationCreateOrChangeFragment()
            val args = Bundle()
            args.putInt("categoryId", categoryId)
            fragment.changeFragment(args, parentFragmentManager)
        }
    }

    private fun updateUI(notifications: List<Notification>) {
        notificationAdapter =
            NotificationAdapter(notifications) { notification -> adapterOnClick(notification) }
        binding.notificationRecyclerView.adapter = notificationAdapter
    }

    private fun adapterOnClick(notification: Notification) {
//        val fragment = NotificationFragment()
//        val args = Bundle()
//        args.putInt(ARG_CATEGORY_ID, category.id)
//        fragment.changeFragment(args, parentFragmentManager)
    }
}