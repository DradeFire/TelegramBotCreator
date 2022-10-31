package com.example.telegrambotcreator.model.creator.helper

import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.model.TextTG
import com.example.telegrambotcreator.model.repository.Repository
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.message
import org.junit.Test

class IF_Else_listenersKtTest {

    @Test
    fun ifElseListener() {
        val bot = Repository().getBotCreator()
        bot.createBaseBot(name = "name", description = "descr")
        bot.builtBot = bot.bot.apply {
            dispatch {
                println("in dispatch")
                message {
                    bot.ifElseListener(
                        TextTG(0, "test", BotCreator.TypeAnswer.TEXT.convertFromType()),
                        "2", CompareOperation.More(), bot.builtBot, this@dispatch, message
                    )
                }
            }
        }.build()
        bot.builtBot.startPolling()

        while (true){}
    }

}