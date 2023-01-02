package com.example.telegrambotcreator.clean.data.datebase.dao

import androidx.room.*
import com.example.telegrambotcreator.creator.model.database.BotDatabaseModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface BotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBot(botDatabaseModel: BotDatabaseModel): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBot(botDatabaseModel: BotDatabaseModel): Completable

    @Delete
    fun deleteBot(botDatabaseModel: BotDatabaseModel): Completable

    @Query("SELECT * FROM bots")
    fun getAllBots(): Observable<List<BotDatabaseModel>>

    @Query("DELETE FROM bots")
    fun deleteAll(): Completable

}