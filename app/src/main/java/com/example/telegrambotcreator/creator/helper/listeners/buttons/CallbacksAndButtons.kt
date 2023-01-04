package com.example.telegrambotcreator.creator.helper.listeners.buttons

import com.example.telegrambotcreator.creator.BotCreator
import com.example.telegrambotcreator.creator.helper.answers.answerListener
import com.example.telegrambotcreator.creator.helper.converters.convertFromCallbackType
import com.example.telegrambotcreator.creator.helper.converters.convertFromTgType
import com.example.telegrambotcreator.creator.helper.converters.convertFromType
import com.example.telegrambotcreator.creator.helper.converters.convertToCallbackType
import com.example.telegrambotcreator.creator.helper.finding.findFather
import com.example.telegrambotcreator.creator.model.ActionTG
import com.example.telegrambotcreator.creator.model.listeners.CallBackTG
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.entities.Message
import com.github.kotlintelegrambot.entities.TelegramFile
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.github.kotlintelegrambot.entities.keyboard.KeyboardButton

    // region Callback and Buttons

/**
 * Добавляет кнопку на ответ
 * @param textButton Текст кнопки
 * @param fatherId Id материнского элемента
 * @param typeAnswer Тип ответа
 * @param answerText Текст ответа
 * @param answerTGFile Файл ответа
 * @param lat Географическая ширина (Latitude)
 * @param lon Географическая долгота (Longitude)
 * @param question Вопрос опроса
 * @param pollList Варианты ответов опроса
 * @param title Заголовок адреса
 * @param address Адрес
 * @param phoneNumber Номер телефона
 * @param firstName Имя владельца телефона
 */
fun BotCreator.addInlineButtonToMessage(
        textButton: String,
        fatherId: Int?,
        typeAnswer: BotCreator.TypeAnswer,
        answerText: String? = null,
        answerTGFile: TelegramFile? = null,
        lat: Float? = null,
        lon: Float? = null,
        question: String? = null,
        pollList: List<String>? = null,
        title: String? = null,
        address: String? = null,
        phoneNumber: String? = null,
        firstName: String? = null
    ) = apply {
        val answerTGFileIn = when(answerTGFile) {
            is TelegramFile.ByUrl -> answerTGFile.url
            is TelegramFile.ByFile -> answerTGFile.file.absolutePath
            is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
            is TelegramFile.ByFileId -> answerTGFile.fileId
            else -> null
        }
        val father = findFather(fatherId ?: -1)
        father?.let { baseTgContainer ->
            val obj = CallBackTG(BotCreator.TypeCallback.INLINE.convertFromCallbackType(), textButton, createNewID(callbackIDs), typeAnswer.convertFromType(), fatherId,
                ActionTG(answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)
            )

            if (baseTgContainer.inCallBack == null)
                baseTgContainer.inCallBack = arrayListOf(obj)
            else
                (baseTgContainer.inCallBack as ArrayList<CallBackTG>).add(obj)
        }

    }

/**
 * Добавляет кнопку под клавиатуру
 * @param textButton Текст кнопки
 * @param fatherId Id материнского элемента
 * @param typeAnswer Тип ответа
 * @param answerText Текст ответа
 * @param answerTGFile Файл ответа
 * @param lat Географическая ширина (Latitude)
 * @param lon Географическая долгота (Longitude)
 * @param question Вопрос опроса
 * @param pollList Варианты ответов опроса
 * @param title Заголовок адреса
 * @param address Адрес
 * @param phoneNumber Номер телефона
 * @param firstName Имя владельца телефона
 */
    fun BotCreator.addReplyButtonToMessage(
        textButton: String,
        fatherId: Int?,
        typeAnswer: BotCreator.TypeAnswer,
        answerText: String? = null,
        answerTGFile: TelegramFile? = null,
        lat: Float? = null,
        lon: Float? = null,
        question: String? = null,
        pollList: List<String>? = null,
        title: String? = null,
        address: String? = null,
        phoneNumber: String? = null,
        firstName: String? = null
    ) = apply {
        val answerTGFileIn = when(answerTGFile) {
            is TelegramFile.ByUrl -> answerTGFile.url
            is TelegramFile.ByFile -> answerTGFile.file.absolutePath
            is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
            is TelegramFile.ByFileId -> answerTGFile.fileId
            else -> null
        }
        val father = findFather(fatherId ?: -1)
        father?.let { baseTgContainer ->
            val obj = CallBackTG(BotCreator.TypeCallback.REPLY.convertFromCallbackType(), textButton,
                createNewID(callbackIDs), typeAnswer.convertFromType(), fatherId,
                ActionTG(
                    answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                    lat, lon, question, pollList, title, address, phoneNumber, firstName
                )
            )

            if (baseTgContainer.inCallBack == null)
                baseTgContainer.inCallBack = arrayListOf(obj)
            else
                (baseTgContainer.inCallBack as ArrayList<CallBackTG>).add(obj)
        }

    }
    internal fun bindInlineButtons(callback: List<CallBackTG>?): ArrayList<List<InlineKeyboardButton>> {
        val inline = arrayListOf<List<InlineKeyboardButton>>()
        callback?.let {
            it.forEach {  call ->
                inline.add(
                    listOf(
                        InlineKeyboardButton.CallbackData(
                            text = call.textButton,
                            callbackData = call.id.toString()
                        )
                    )
                )
            }
        }
        return inline
    }
    internal fun bindReplyButtons(callback: List<CallBackTG>?): ArrayList<List<KeyboardButton>> {
        val reply = arrayListOf<List<KeyboardButton>>()
        callback?.let {
            it.forEach {  call ->
                reply.add(
                    listOf(
                        KeyboardButton(
                            text = call.textButton
                        )
                    )
                )
            }
        }
        return reply
    }
    internal fun BotCreator.addCallback(
        dispatcher: Dispatcher,
        callbacks: List<CallBackTG>,
        message: Message
    ): Boolean = with(dispatcher)  {
        callbacks.forEach { call ->
            when(call.typeCallback.convertToCallbackType()){
                BotCreator.TypeCallback.INLINE -> {
                    callbackQuery(callbackData = call.id.toString()) {
                        answerListener(call, this.bot, this@with, message)
                    }
                }
                BotCreator.TypeCallback.REPLY -> {
                    callbackQuery(callbackData = call.textButton) {
                        answerListener(call, this.bot, this@with, message)
                    }
                }
            }
        }
        return true
    }
    // endregion