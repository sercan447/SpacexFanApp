package com.example.spacexfanapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.spacexfanapp.entity.FavoriteEntity

@Dao
interface FavoriteDao {


    @Query("SELECT * FROM favoriteEntity")
    fun getAll():List<FavoriteEntity>

    @Query("SELECT * FROM favoriteEntity WHERE uid=:uid")
    fun findById(uid:String): FavoriteEntity

    @Insert
    fun insert(vararg todo : FavoriteEntity)

    @Delete
    fun delete(todo:FavoriteEntity)

    @Query("DELETE FROM favoriteEntity WHERE uid=:uid")
    fun deleteId(uid:String): Int

}