package com.troy.actionbutton.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.troy.actionbutton.repository.entity.ActionEntity
import com.troy.actionbutton.repository.dao.ActionDAO

@Database(entities = [ActionEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun actionsDao(): ActionDAO

    companion object {

        private const val DB_NAME = "QUAFFED_ROOM_DB"

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}