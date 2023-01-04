package com.example.telegrambotcreator.creator.helper

import com.example.telegrambotcreator.creator.BotCreator
import com.example.telegrambotcreator.creator.helper.converters.convertFromType
import com.example.telegrambotcreator.creator.helper.listeners.ifelse.CompareOperation
import com.example.telegrambotcreator.creator.helper.listeners.ifelse.ifElseListener
import com.example.telegrambotcreator.creator.model.ActionTG
import com.example.telegrambotcreator.creator.model.listeners.TextTG
import com.example.telegrambotcreator.repository.Repository
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
                        TextTG(0, message.text ?: "", BotCreator.TypeAnswer.TEXT.convertFromType(),
                            action = ActionTG(
                            "yes"
                            ),
                            elseAction = ActionTG(
                                "no"
                            )
                        ),
                        "kek", CompareOperation.Equal(), bot.builtBot, this@dispatch, message
                    )
                }
            }
        }.build()
        bot.builtBot.startPolling()

        while (true) {
        }
    }

}