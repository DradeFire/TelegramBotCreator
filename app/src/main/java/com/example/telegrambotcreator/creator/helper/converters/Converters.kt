package com.example.telegrambotcreator.creator.helper.converters

import com.example.telegrambotcreator.creator.BotCreator
import com.github.kotlintelegrambot.entities.TelegramFile
import java.io.File

// region Converters

/**
 * Конвертирует строку в тип ответа
 */
internal fun String.convertToType(): BotCreator.TypeAnswer = when (this) {
    "TEXT" -> BotCreator.TypeAnswer.TEXT
    "ANIMATION" -> BotCreator.TypeAnswer.ANIMATION
    "AUDIO" -> BotCreator.TypeAnswer.AUDIO
    "DOCUMENT" -> BotCreator.TypeAnswer.DOCUMENT
    "PHOTO" -> BotCreator.TypeAnswer.PHOTO
    "VIDEO" -> BotCreator.TypeAnswer.VIDEO
    "VOICE" -> BotCreator.TypeAnswer.VOICE
    "CONTACT" -> BotCreator.TypeAnswer.CONTACT
    "LOCATION" -> BotCreator.TypeAnswer.LOCATION
    "POLL" -> BotCreator.TypeAnswer.POLL
    "VENUE" -> BotCreator.TypeAnswer.VENUE
    else -> BotCreator.TypeAnswer.VIDEO_NOTE
}

/**
 * Конвертирует тип ответа в строку
 */
internal fun BotCreator.TypeAnswer.convertFromType(): String = when (this) {
    BotCreator.TypeAnswer.TEXT -> "TEXT"
    BotCreator.TypeAnswer.ANIMATION -> "ANIMATION"
    BotCreator.TypeAnswer.AUDIO -> "AUDIO"
    BotCreator.TypeAnswer.DOCUMENT -> "DOCUMENT"
    BotCreator.TypeAnswer.PHOTO -> "PHOTO"
    BotCreator.TypeAnswer.VIDEO -> "VIDEO"
    BotCreator.TypeAnswer.VOICE -> "VOICE"
    BotCreator.TypeAnswer.CONTACT -> "CONTACT"
    BotCreator.TypeAnswer.LOCATION -> "LOCATION"
    BotCreator.TypeAnswer.POLL -> "POLL"
    BotCreator.TypeAnswer.VENUE -> "VENUE"
    else -> "VIDEO_NOTE"
}

/**
 * Конвертирует строку в вариант получения телеграм файла
 */
internal fun String.convertToTgType(fileOrSmth: String): TelegramFile = when (this) {
    "ByFile" -> TelegramFile.ByFile(File(fileOrSmth))
    "ByUrl" -> TelegramFile.ByUrl(fileOrSmth)
    "ByByteArray" -> TelegramFile.ByByteArray(fileOrSmth.toByteArray())
    else -> TelegramFile.ByFileId(fileOrSmth)
}

/**
 * Конвертирует вариант получения телеграм файла в строку
 */
internal fun TelegramFile.convertFromTgType(): String = when (this) {
    is TelegramFile.ByFile -> "ByFile"
    is TelegramFile.ByUrl -> "ByUrl"
    is TelegramFile.ByByteArray -> "ByByteArray"
    else -> "ByFileId"
}

/**
 * Конвертирует содержание файла в строку
 */
internal fun TelegramFile.convertTgFileToString(): String? = when(this) {
        is TelegramFile.ByUrl -> this.url
        is TelegramFile.ByFile -> this.file.absolutePath
        is TelegramFile.ByByteArray -> this.fileBytes.toString()
        is TelegramFile.ByFileId -> this.fileId
        else -> null
    }

/**
 * Конвертирует строку в тип кнопки
 */
internal fun String.convertToCallbackType(): BotCreator.TypeCallback = when (this) {
    "INLINE" -> BotCreator.TypeCallback.INLINE
    else -> BotCreator.TypeCallback.REPLY
}

/**
 * Конвертирует тип кнопки в строку
 */
internal fun BotCreator.TypeCallback.convertFromCallbackType(): String = when (this) {
    BotCreator.TypeCallback.INLINE -> "INLINE"
    else -> "REPLY"
}
// endregion