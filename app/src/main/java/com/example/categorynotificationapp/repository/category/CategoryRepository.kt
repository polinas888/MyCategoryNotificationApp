package com.example.categorynotificationapp.repository.category

import com.example.categorynotificationapp.model.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(categoryDao: CategoryDao): CategoryDao {
    private val categoryDao: CategoryDao by lazy {
        categoryDao
    }

    override suspend fun getCategories() : List<Category> = categoryDao.getCategories()

    override suspend fun addCategory(category: Category) = categoryDao.addCategory(category)
}