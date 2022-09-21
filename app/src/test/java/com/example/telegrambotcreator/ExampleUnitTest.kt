package com.example.telegrambotcreator

import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.helper.addChildCommandListener
import com.example.telegrambotcreator.model.creator.helper.addCommandListener
import com.example.telegrambotcreator.model.creator.helper.addInlineButtonToMessage
import com.example.telegrambotcreator.model.repository.Repository
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun creation_tg_bot(){
        val bot = Repository().getBotCreator()
        bot.apply {
            createBaseBot(name = "kek_bot", description = "123")
            addCommandListener(command = "start", answerText = "ok", typeAnswer = BotCreator.TypeAnswer.TEXT)
            var ids = getIDs()
            addChildCommandListener("lol_rus", ids.last(), BotCreator.TypeAnswer.TEXT, "Ну дурачок, правда")
            ids = getIDs()
            addInlineButtonToMessage("Еблан?", ids.first(), BotCreator.TypeAnswer.TEXT, "Ну EBLAN")
//            build().startPolling()
        }

        while (true) {}
    }

}