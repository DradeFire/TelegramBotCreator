package com.example.telegrambotcreator.creator.helper.listeners.repeat

import com.example.telegrambotcreator.creator.BotCreator
import com.example.telegrambotcreator.creator.helper.answers.answerListener
import com.example.telegrambotcreator.creator.helper.listeners.base.*
import com.example.telegrambotcreator.creator.helper.listeners.base.addAnimation
import com.example.telegrambotcreator.creator.helper.listeners.base.addCommands
import com.example.telegrambotcreator.creator.helper.listeners.base.addDocument
import com.example.telegrambotcreator.creator.helper.listeners.base.addText
import com.example.telegrambotcreator.creator.model.listeners.*
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.entities.Message
/**
 * Создаёт дублирующийся ответ пользователю
 * @param listenResult Листенер с ответом
 * @param countRepeat Количество повторов ответа
 */
fun BotCreator.repeaterListener(
    listenResult: ListenerTgBase,
    countRepeat: Int,
    bot: Bot,
    dispatcher: Dispatcher,
    message: Message
) = apply {
    repeat(countRepeat) {
        answerListener(listenResult, bot, dispatcher, message)
    }

    listenResult.inListeners?.forEach { listener ->
        when (listener) {
            is AnimationTG -> addAnimation(dispatcher, listener)
            is CommandTG -> addCommands(dispatcher, listener)
            is ContactTG -> addContacts(dispatcher, listener)
            is DocumentTG -> addDocument(dispatcher, listener)
            is LocationTG -> addLocations(dispatcher, listener)
            is PhotoTG -> addPhotos(dispatcher, listener)
            is StickerTG -> addSticker(dispatcher, listener)
            is TextTG -> addText(dispatcher, listener)
            is VideoNoteTG -> addVideoNotes(dispatcher, listener)
            is VideoTG -> addVideos(dispatcher, listener)
            is VoiceTG -> addVoices(dispatcher, listener)
            else -> throw Exception()
        }
    }
}