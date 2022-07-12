package com.example.categorynotificationapp.ui.category

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categorynotificationapp.MainActivity
import com.example.categorynotificationapp.R
import com.example.categorynotificationapp.changeFragment
import com.example.categorynotificationapp.databinding.FragmentCategoryBinding
import com.example.categorynotificationapp.model.Category
import com.example.categorynotificationapp.ui.notification.NotificationFragment
import com.google.gson.GsonBuilder

const val CREATE_CATEGORY_FRAGMENT = 1
const val ARG_CATEGORY: String = "CATEGORY"

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private val categoryViewModel by viewModels<CategoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.toolbar_title_category)

        categoryViewModel.loadData()
        categoryViewModel.categoryListLiveData.observe(viewLifecycleOwner, Observer { categories ->
            if (categories.isEmpty()) {
                binding.emptyListText.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            } else {
                updateUI(categories)
                binding.progressBar.visibility = View.GONE
            }
        })

        binding.addButton.setOnClickListener {
            val dialog = CreateCategoryDialogFragment()
            dialog.setTargetFragment(this, CREATE_CATEGORY_FRAGMENT)
            dialog.show(parentFragmentManager, "CustomDialog")
        }
    }

    private fun updateUI(categories: List<Category>) {
        categoryAdapter = CategoryAdapter (categories) { category -> adapterOnClick(category) }
        binding.categoryRecyclerView.adapter = categoryAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CREATE_CATEGORY_FRAGMENT -> {
                binding.progressBar.visibility = View.VISIBLE
                if (resultCode == Activity.RESULT_OK) {
                    val bundle = data?.extras
                    val category = bundle?. getString ("category")
                    val newCategory = category?.let { categoryName -> Category(name = categoryName) }

                    if (newCategory != null) {
                        categoryViewModel.saveCategory(newCategory)
                    }
                    categoryViewModel.loadData()
                } else {
                     Log.i("Error", "Bad result")
                }
            }
        }
    }

    private fun adapterOnClick(category: Category) {
        val fragment = NotificationFragment()
        val args = Bundle()
        val builder = GsonBuilder()
        val gson = builder.create()
        val result: String = gson.toJson(category)

        args.putString(ARG_CATEGORY, result)
        fragment.changeFragment(args, parentFragmentManager)
    }
}