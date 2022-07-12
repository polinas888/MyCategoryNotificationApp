package com.example.categorynotificationapp.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.categorynotificationapp.databinding.ItemCatergoryBinding
import com.example.categorynotificationapp.model.Category

class CategoryAdapter(
    private val onItemClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private val listCategories: MutableList<Category> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCatergoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding, onItemClick)
    }

    fun setData(listCategory: List<Category>) {
        listCategories.clear()
        listCategories.addAll(listCategory)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        getCategory(position).also {
            viewHolder.bind(it)
        }

        viewHolder.itemView.setOnClickListener { onItemClick(listCategories[position]) }
    }

    override fun getItemCount() = listCategories.size

    private fun getCategory(position: Int): Category {
        return listCategories[position]
    }

    inner class ViewHolder(
        private val binding: ItemCatergoryBinding,
        val onClick: (Category) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.category = category
        }
    }
}