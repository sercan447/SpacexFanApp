package com.example.spacexfanapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spacexfanapp.dao.FavoriteDao
import com.example.spacexfanapp.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class],version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao():FavoriteDao


    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,AppDatabase::class.java,"favorite-list.db")
            .build()

        fun getDatabase(context: Context?): AppDatabase {
            val tempInstance = instance
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val inst = Room.databaseBuilder(context!!.applicationContext,AppDatabase::class.java,"favoriteEntity")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()

                instance = inst
                return inst
            }
        }
    }


}