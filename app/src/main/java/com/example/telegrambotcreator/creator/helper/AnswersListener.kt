package com.example.telegrambotcreator.creator.helper

import com.example.telegrambotcreator.creator.BotCreator
import com.example.telegrambotcreator.creator.model.*
import com.example.telegrambotcreator.creator.model.listeners.*
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.entities.*

/**
 * Отправляет ответ, задаёт кнопки и задаёт листенеры для вложенных ответов
 * @param obj Листенер
 */
internal fun BotCreator.answerListener(
    obj: ListenerTgBase,
    bot: Bot,
    dispatcher: Dispatcher,
    message: Message
) {
    val inL = when (checkAndGetButtons(obj.inCallBack)) {
        BotCreator.TypeCallback.REPLY -> KeyboardReplyMarkup(bindReplyButtons(obj.inCallBack))
        BotCreator.TypeCallback.INLINE -> InlineKeyboardMarkup.create(bindInlineButtons(obj.inCallBack))
        else -> null
    }

    if (obj.isTrue == null || obj.isTrue == true) {
        sendAnswer(obj, obj.action, bot, message, inL)
    } else {
        sendAnswer(obj, obj.elseAction, bot, message, inL)
    }

    obj.inCallBack?.let { addCallback(dispatcher, it, message) }
    obj.inListeners?.forEach { listener ->
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
//    obj.inAnimation?.let { addAnimation(dispatcher, it) }
//    obj.inContact?.let { addContacts(dispatcher, it) }
//    obj.inDocument?.let { addDocument(dispatcher, it) }
//    obj.inLocation?.let { addLocations(dispatcher, it) }
//    obj.inPhoto?.let { addPhotos(dispatcher, it) }
//    obj.inSticker?.let { addSticker(dispatcher, it) }
//    obj.inText?.let { addText(dispatcher, it) }
//    obj.inVideoNote?.let { addVideoNotes(dispatcher, it) }
//    obj.inVideo?.let { addVideos(dispatcher, it) }
//    obj.inVoice?.let { addVoices(dispatcher, it) }
//    obj.inCommand?.let { addCommands(dispatcher, it) }
}

/**
 * Отправляет ответ пользователю
 * @param obj Листенер
 * @param action Отправляемое сообщение
 * @param inL Кнопка
 */
fun sendAnswer(
    obj: ListenerTgBase,
    action: ActionTG?,
    bot: Bot,
    message: Message,
    inL: ReplyMarkup?
) {
    when (obj.typeAnswer.convertToType()) {
        BotCreator.TypeAnswer.TEXT -> {
            bot.sendMessage(
                ChatId.fromId(message.chat.id),
                text = action?.answerText!!, replyMarkup = inL
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            bot.sendAnimation(
                ChatId.fromId(message.chat.id), animation = action?.tgFileType!!
                    .convertToTgType(action.answerTGFile!!), replyMarkup = inL
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            bot.sendAudio(
                ChatId.fromId(message.chat.id),
                action?.tgFileType!!.convertToTgType(action.answerTGFile!!), replyMarkup = inL
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            bot.sendDocument(
                ChatId.fromId(message.chat.id),
                action?.tgFileType!!.convertToTgType(action.answerTGFile!!), replyMarkup = inL
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            bot.sendPhoto(
                ChatId.fromId(message.chat.id),
                action?.tgFileType!!.convertToTgType(action.answerTGFile!!), replyMarkup = inL
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            bot.sendVideo(
                ChatId.fromId(message.chat.id),
                action?.tgFileType!!.convertToTgType(action.answerTGFile!!), replyMarkup = inL
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            bot.sendVoice(
                ChatId.fromId(message.chat.id),
                action?.tgFileType!!.convertToTgType(action.answerTGFile!!), replyMarkup = inL
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            bot.sendContact(
                ChatId.fromId(message.chat.id),
                action?.phoneNumber!!, action.firstName!!, replyMarkup = inL
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            bot.sendLocation(
                ChatId.fromId(message.chat.id),
                action?.lat!!, action.lon!!, replyMarkup = inL
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            bot.sendPoll(
                ChatId.fromId(message.chat.id),
                action?.question!!, action.pollList!!, replyMarkup = inL
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            bot.sendVenue(
                ChatId.fromId(message.chat.id),
                action?.lat!!, action.lon!!, action.title!!,
                action.address!!, replyMarkup = inL
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            bot.sendVideoNote(
                ChatId.fromId(message.chat.id),
                action?.tgFileType!!
                    .convertToTgType(action.answerTGFile!!) as TelegramFile.ByFile,
                replyMarkup = inL
            )
        }
    }
}

internal fun checkAndGetButtons(inCallBack: List<CallBackTG>?): BotCreator.TypeCallback? {
    inCallBack?.let { list ->
        return if (list.isNotEmpty()) {
            return when (list[0].typeCallback.convertToCallbackType()) {
                BotCreator.TypeCallback.INLINE -> {
                    BotCreator.TypeCallback.INLINE
                }
                BotCreator.TypeCallback.REPLY -> {
                    BotCreator.TypeCallback.REPLY
                }
            }
        } else null
    }
    return null
}
