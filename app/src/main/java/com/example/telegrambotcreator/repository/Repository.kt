package com.example.telegrambotcreator.repository

import android.content.Context
import com.example.telegrambotcreator.creator.BotCreator
import com.example.telegrambotcreator.clean.data.datebase.database.BotDatabase
import com.example.telegrambotcreator.creator.model.database.BotDatabaseModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.lang.Exception

// TODO: Разбить на несколько репозиториев
class Repository {

    fun getBotCreator(): BotCreator = BotCreator()

    fun getDatabase(context: Context): BotDatabase = BotDatabase.getInstance(context)

    fun addBot(database: BotDatabase, bot: BotDatabaseModel?): Completable {
        bot?.let {
            return database.dao().addBot(bot)
        }
        throw Exception()
    }

    fun updateBot(database: BotDatabase, bot: BotDatabaseModel?): Completable {
        bot?.let {
            return database.dao().updateBot(bot)
        }
        throw Exception()
    }

    fun deleteBot(database: BotDatabase, bot: BotDatabaseModel): Completable = database.dao().deleteBot(bot)

    fun getAllBots(database: BotDatabase): Observable<List<BotDatabaseModel>> = database.dao().getAllBots()

    fun deleteAll(database: BotDatabase): Completable = database.dao().deleteAll()

}