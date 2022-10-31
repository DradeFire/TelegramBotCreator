package com.example.telegrambotcreator.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.telegrambotcreator.App
import com.example.telegrambotcreator.consts.SingleLiveEvent
import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.model.ListenerTgBase
import com.example.telegrambotcreator.model.datebase.database.BotDatabase
import com.example.telegrambotcreator.model.datebase.model.BotDbModel
import com.example.telegrambotcreator.model.repository.Repository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.ArrayDeque

class TelegramViewModel: ViewModel() {

    var router: Router? = App.INSTANCE.router

    private val repository by lazy {
        Repository()
    }
    private lateinit var database: BotDatabase

    // region LiveData
    val allBots: SingleLiveEvent<List<BotDbModel>> by lazy {
        SingleLiveEvent()
    }
    val updateTrigger: SingleLiveEvent<Boolean> by lazy {
        SingleLiveEvent()
    }
    val addTrigger: SingleLiveEvent<Boolean> by lazy {
        SingleLiveEvent()
    }
    val deleteTrigger: SingleLiveEvent<Boolean> by lazy {
        SingleLiveEvent()
    }
    // endregion

    // region Funcs
    fun initDatabase(context: Context){
        database = repository.getDatabase(context)
    }

    fun addBot(bot: BotDbModel?) {
        repository.addBot(database, bot)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                addTrigger.value = true
            }, {
                addTrigger.value = false
            })
    }

    fun updateBot(bot: BotDbModel?){
        repository.updateBot(database, bot)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateTrigger.value = true
            }, {
                updateTrigger.value = false
            })
    }

    fun deleteBot(bot: BotDbModel){
        repository.deleteBot(database, bot)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                deleteTrigger.value = true
            }, {
                deleteTrigger.value = false
            })
    }

    fun getAllBots(){
        repository.getAllBots(database)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                allBots.value = it
            }, {
                allBots.value = null
            })
    }
    // endregion

    // region Bot
    var chosenBot: BotCreator = repository.getBotCreator()
    // endregion

    // region Command
    var choosenCommand = 0
    val commandsDeque = ArrayDeque<ListenerTgBase>()
    // endregion

    // region Creating Command
    var isCreatingCommand = false
    var isCreatingCallbackButton = false
    // endregion

    override fun onCleared() {
        router = null
        super.onCleared()
    }

}