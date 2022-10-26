package com.example.telegrambotcreator.model.repository

import android.content.Context
import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.datebase.database.BotDatabase
import com.example.telegrambotcreator.model.datebase.model.BotDbModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.lang.Exception

class Repository {

    fun getBotCreator(): BotCreator = BotCreator()

    fun getDatabase(context: Context): BotDatabase = BotDatabase.getInstance(context)

    fun addBot(database: BotDatabase, bot: BotDbModel?): Completable {
        bot?.let {
            return database.dao().addBot(bot)
        }
        throw Exception()
    }

    fun updateBot(database: BotDatabase, bot: BotDbModel?): Completable {
        bot?.let {
            return database.dao().updateBot(bot)
        }
        throw Exception()
    }

    fun deleteBot(database: BotDatabase, bot: BotDbModel): Completable = database.dao().deleteBot(bot)

    fun getAllBots(database: BotDatabase): Observable<List<BotDbModel>> = database.dao().getAllBots()

    fun deleteAll(database: BotDatabase): Completable = database.dao().deleteAll()

}