package com.example.telegrambotcreator.creator.helper

import com.example.telegrambotcreator.creator.BotCreator
import com.example.telegrambotcreator.creator.helper.converters.convertFromType
import com.example.telegrambotcreator.creator.helper.listeners.repeat.repeaterListener
import com.example.telegrambotcreator.creator.model.ActionTG
import com.example.telegrambotcreator.creator.model.listeners.TextTG
import com.example.telegrambotcreator.repository.Repository
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.message

import org.junit.Test

class RepeaterListenersKtTest {

    @Test
    fun repeaterListener() {
        val bot = Repository().getBotCreator()
        bot.createBaseBot(name = "name", description = "descr")
        bot.builtBot = bot.bot.apply {
            dispatch {
                println("in dispatch")
                message {
                    bot.repeaterListener(
                        TextTG(
                            0,
                            "test",
                            BotCreator.TypeAnswer.TEXT.convertFromType(),
                            null,
                            ActionTG("kek $")
                        ),
                        5, bot.builtBot, this@dispatch, message
                    )
                }
            }
        }.build()
        bot.builtBot.startPolling()

        while (true) {
        }
    }
}