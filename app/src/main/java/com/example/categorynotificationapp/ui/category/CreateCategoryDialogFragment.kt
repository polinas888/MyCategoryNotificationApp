package com.example.categorynotificationapp.ui.category

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.categorynotificationapp.databinding.FragmentCreateCategoryDialogBinding

class CreateCategoryDialogFragment: DialogFragment() {
    private lateinit var binding: FragmentCreateCategoryDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateCategoryDialogBinding.inflate(layoutInflater)
        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.okButton.setOnClickListener {
            val category = binding.createCategoryEditText.text.toString()
            if(category.isNotEmpty()) {
                val i: Intent = Intent()
                    .putExtra("category", category)
                targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, i)
            }
            dismiss()
        }
        return binding.root
    }
}