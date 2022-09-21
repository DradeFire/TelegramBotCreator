package com.example.telegrambotcreator.model.datebase.dao

import androidx.room.*
import com.example.telegrambotcreator.model.datebase.model.BotDbModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface BotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBot(botDbModel: BotDbModel): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBot(botDbModel: BotDbModel): Completable

    @Delete
    fun deleteBot(botDbModel: BotDbModel): Completable

    @Query("SELECT * FROM bots")
    fun getAllBots(): Observable<List<BotDbModel>>

    @Query("DELETE FROM bots")
    fun deleteAll(): Completable

}