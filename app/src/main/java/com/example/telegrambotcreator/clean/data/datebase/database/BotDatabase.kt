package com.example.telegrambotcreator.clean.data.datebase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.telegrambotcreator.clean.data.datebase.dao.BotDao
import com.example.telegrambotcreator.creator.model.database.BotDatabaseModel

@Database(entities = [BotDatabaseModel::class], version = 1, exportSchema = false,
    //autoMigrations = [AutoMigration(from = 1, to = 2)]
)
abstract class BotDatabase: RoomDatabase() {
    abstract fun dao(): BotDao

    companion object {
        @Volatile
        private var INSTANCE: BotDatabase? = null

        fun getInstance(context: Context): BotDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, BotDatabase::class.java, "bots.db").build()
    }

}