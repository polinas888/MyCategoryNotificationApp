package com.example.categorynotificationapp.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.categorynotificationapp.model.Category

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    suspend fun getCategories(): List<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategory(category: Category)
}