package com.example.categorynotificationapp.ui.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.categorynotificationapp.databinding.ItemNotificationBinding
import com.example.categorynotificationapp.model.Notification

class NotificationAdapter(
    private val listNotifications: List<Notification>,
    private val onClickDelete: (Notification) -> Unit
) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNotificationBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        getNotification(position).also {
            viewHolder.bind(it)
        }
    }

    override fun getItemCount() = listNotifications.size

    private fun getNotification(position: Int): Notification {
        return listNotifications[position]
    }

    inner class ViewHolder(
        private val binding: ItemNotificationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(notification: Notification) {
            binding.notification = notification

            binding.deleteNotification.setOnClickListener {
                  onClickDelete(notification)
            }
        }
    }
}
