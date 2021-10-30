package com.example.noteapproom.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDao {
    @Query("SELECT * FROM Recipe ORDER BY title DESC")
    fun getAllUserInfo(): List<Recipe>

    @Insert
    fun insertrecipe(n: Recipe)
    @Update
    fun updaterecipe(recipe: Recipe)
    @Delete
    fun deleterecipe(recipe: Recipe)

}
