package com.example.telegrambotcreator.model.creator.helper

import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.model.BaseTgContainer
import com.example.telegrambotcreator.model.creator.model.CallBackTG
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.entities.*

internal fun BotCreator.answerListener(obj: BaseTgContainer, bot: Bot, dispatcher: Dispatcher, message: Message) {
    val inL = when (checkAndGetButtons(obj.inCallBack)){
        BotCreator.TypeCallback.REPLY -> KeyboardReplyMarkup(bindReplyButtons(obj.inCallBack))
        BotCreator.TypeCallback.INLINE -> InlineKeyboardMarkup.create(bindInlineButtons(obj.inCallBack))
        else -> null
    }

    when(obj.typeAnswer.convertToType()) {
        BotCreator.TypeAnswer.TEXT -> {
            bot.sendMessage(ChatId.fromId(message.chat.id), text = obj.answerText!!, replyMarkup = inL)
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            bot.sendAnimation(ChatId.fromId(message.chat.id), animation = obj.tgFileType!!.convertToTgType(obj.answerTGFile!!), replyMarkup = inL)
        }
        BotCreator.TypeAnswer.AUDIO -> {
            bot.sendAudio(ChatId.fromId(message.chat.id), obj.tgFileType!!.convertToTgType(obj.answerTGFile!!), replyMarkup = inL)
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            bot.sendDocument(ChatId.fromId(message.chat.id), obj.tgFileType!!.convertToTgType(obj.answerTGFile!!), replyMarkup = inL)
        }
        BotCreator.TypeAnswer.PHOTO -> {
            bot.sendPhoto(ChatId.fromId(message.chat.id), obj.tgFileType!!.convertToTgType(obj.answerTGFile!!), replyMarkup = inL)
        }
        BotCreator.TypeAnswer.VIDEO -> {
            bot.sendVideo(ChatId.fromId(message.chat.id), obj.tgFileType!!.convertToTgType(obj.answerTGFile!!), replyMarkup = inL)
        }
        BotCreator.TypeAnswer.VOICE -> {
            bot.sendVoice(ChatId.fromId(message.chat.id), obj.tgFileType!!.convertToTgType(obj.answerTGFile!!), replyMarkup = inL)
        }
        BotCreator.TypeAnswer.CONTACT -> {
            bot.sendContact(ChatId.fromId(message.chat.id), obj.phoneNumber!!, obj.firstName!!, replyMarkup = inL)
        }
        BotCreator.TypeAnswer.LOCATION -> {
            bot.sendLocation(ChatId.fromId(message.chat.id), obj.lat!!, obj.lon!!, replyMarkup = inL)
        }
        BotCreator.TypeAnswer.POLL -> {
            bot.sendPoll(ChatId.fromId(message.chat.id), obj.question!!, obj.pollList!!, replyMarkup = inL)
        }
        BotCreator.TypeAnswer.VENUE -> {
            bot.sendVenue(ChatId.fromId(message.chat.id), obj.lat!!, obj.lon!!, obj.title!!, obj.address!!, replyMarkup = inL)
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            bot.sendVideoNote(ChatId.fromId(message.chat.id), obj.tgFileType!!.convertToTgType(obj.answerTGFile!!) as TelegramFile.ByFile, replyMarkup = inL)
        }
    }

    obj.inCallBack?.let { addCallback(dispatcher, it, message) }
    obj.inAnimation?.let { addAnimation(dispatcher, it) }
    obj.inContact?.let { addContacts(dispatcher, it) }
    obj.inDocument?.let { addDocument(dispatcher, it) }
    obj.inLocation?.let { addLocations(dispatcher, it) }
    obj.inPhoto?.let { addPhotos(dispatcher, it) }
    obj.inSticker?.let { addSticker(dispatcher, it) }
    obj.inText?.let { addText(dispatcher, it) }
    obj.inVideoNote?.let { addVideoNotes(dispatcher, it) }
    obj.inVideo?.let { addVideos(dispatcher, it) }
    obj.inVoice?.let { addVoices(dispatcher, it) }
    obj.inCommand?.let { addCommands(dispatcher, it) }
}

internal fun checkAndGetButtons(inCallBack: List<CallBackTG>?): BotCreator.TypeCallback? {
    inCallBack?.let { list ->
        return if (list.isNotEmpty()){
            return when(list[0].typeCallback.convertToCallbackType()){
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
