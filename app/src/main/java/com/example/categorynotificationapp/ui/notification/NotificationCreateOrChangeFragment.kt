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
import com.example.categorynotificationapp.changeFragment
import com.example.categorynotificationapp.databinding.FragmentNotificationCreateOrChangeBinding
import com.example.categorynotificationapp.ui.category.ARG_CATEGORY_ID

class NotificationCreateOrChangeFragment : Fragment() {
    private lateinit var binding: FragmentNotificationCreateOrChangeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationCreateOrChangeBinding.inflate(layoutInflater)
        val categoryId = arguments?.getInt(ARG_CATEGORY_ID)
        binding.cancelButton.setOnClickListener {
            val fragment = NotificationFragment()
            arguments?.let { args -> fragment.changeFragment(args, parentFragmentManager ) }
        }

        binding.okButton.setOnClickListener {
            val notification = binding.createOrEditNotificationEditText.text.toString()
            setFragmentResult("requestKey", bundleOf("bundleKey" to notification))
            val fragment = NotificationFragment()
            val args = Bundle()
            if (categoryId != null) {
                args.putInt(ARG_CATEGORY_ID, categoryId)
            }
            val manager: FragmentManager = requireActivity().supportFragmentManager
            val trans: FragmentTransaction = manager.beginTransaction()
            trans.remove(this)
            trans.commit()
            manager.popBackStack()
        }
        return binding.root
    }
}