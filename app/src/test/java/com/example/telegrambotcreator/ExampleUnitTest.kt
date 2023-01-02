package com.example.telegrambotcreator

import com.example.telegrambotcreator.creator.BotCreator
import com.example.telegrambotcreator.creator.helper.addChildCommandListener
import com.example.telegrambotcreator.creator.helper.addCommandListener
import com.example.telegrambotcreator.creator.helper.addInlineButtonToMessage
import com.example.telegrambotcreator.repository.Repository
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun creation_tg_bot() {
        val bot = Repository().getBotCreator()
        bot.apply {
            createBaseBot(name = "kek_bot", description = "123")
            addCommandListener(
                command = "start",
                answerText = "ok",
                typeAnswer = BotCreator.TypeAnswer.TEXT
            )
            var ids = getIDs()
            addChildCommandListener(
                "lol_rus",
                ids.last(),
                BotCreator.TypeAnswer.TEXT,
                "Ну дурачок, правда"
            )
            ids = getIDs()
            addInlineButtonToMessage("Еблан?", ids.first(), BotCreator.TypeAnswer.TEXT, "Ну EBLAN")
            build()
            builtBot.startPolling()
        }

        while (true) {
        }
    }

//    data class Some(var txt: String, var i: Int)
//
//    @Test
//    fun testRefs() {
//        val el1 = Some("123", 123)
//
//        val arr = arrayListOf(el1)
//        println(el1 == arr[0])
//        println(el1 === arr[0])
//
//        el1.i = 321
//        el1.txt = "321"
//        println(arr[0].toString())
//
//        arr[0].i = 123
//        arr[0].txt = "123"
//        println(el1.toString())
//    }

}