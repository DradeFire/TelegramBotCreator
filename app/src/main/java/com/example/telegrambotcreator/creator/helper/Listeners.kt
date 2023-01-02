package com.example.telegrambotcreator.creator.helper

import com.example.telegrambotcreator.creator.BotCreator
import com.example.telegrambotcreator.creator.model.*
import com.example.telegrambotcreator.creator.model.listeners.*
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.entities.TelegramFile

// region Command

/**
 * Добавляет листенер команд
 * @param command Команда листенера, для пользователя буде выглядеть "/команда"
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
fun BotCreator.addCommandListener(
    command: String,
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
    val answerTGFileIn = answerTGFile?.convertTgFileToString()
    commands.add(
        CommandTG(
            createNewID(), command, typeAnswer.convertFromType(), null,
            ActionTG(
                answerText,
                answerTGFileIn,
                answerTGFile?.convertFromTgType(),
                lat,
                lon,
                question,
                pollList,
                title,
                address,
                phoneNumber,
                firstName
            )
        )
    )
}

internal fun BotCreator.addCommands(dispatcher: Dispatcher, command: CommandTG): Boolean =
    with(dispatcher) {
        command(command.command) {
            answerListener(command, this.bot, this@with, message)
        }
        return true
    }

// endregion
// region Text
/**
 * Добавляет листенер обычного текста
 * @param text Команда листенера, для пользователя буде выглядеть "команда"
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
fun BotCreator.addTextListener(
    text: String,
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
    val answerTGFileIn = answerTGFile?.convertTgFileToString()
    texts.add(
        TextTG(
            createNewID(), text, typeAnswer.convertFromType(), null,
            ActionTG(
                answerText,
                answerTGFileIn,
                answerTGFile?.convertFromTgType(),
                lat,
                lon,
                question,
                pollList,
                title,
                address,
                phoneNumber,
                firstName
            )
        )
    )
}

internal fun BotCreator.addText(dispatcher: Dispatcher, command: TextTG): Boolean =
    with(dispatcher) {
        text(command.text) {
            answerListener(command, this.bot, this@with, message)
        }
        return true
    }

// endregion
// region Animation
/**
 * Добавляет листенер анимации
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
fun BotCreator.addAnimationListener(
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
    val answerTGFileIn = answerTGFile?.convertTgFileToString()
    animations.add(
        AnimationTG(
            createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText,
                answerTGFileIn,
                answerTGFile?.convertFromTgType(),
                lat,
                lon,
                question,
                pollList,
                title,
                address,
                phoneNumber,
                firstName
            )
        )
    )
}

internal fun BotCreator.addAnimation(dispatcher: Dispatcher, command: AnimationTG): Boolean =
    with(dispatcher) {
        animation {
            answerListener(command, this.bot, this@with, message)
        }
        return true
    }

// endregion
// region Document
/**
 * Добавляет листенер документа
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
fun BotCreator.addDocumentListener(
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
    val answerTGFileIn = answerTGFile?.convertTgFileToString()
    documents.add(
        DocumentTG(
            createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText,
                answerTGFileIn,
                answerTGFile?.convertFromTgType(),
                lat,
                lon,
                question,
                pollList,
                title,
                address,
                phoneNumber,
                firstName
            )
        )
    )
}

internal fun BotCreator.addDocument(dispatcher: Dispatcher, com: ListenerTgBase): Boolean =
    with(dispatcher) {
        document {
            answerListener(com, this.bot, this@with, message)
        }
        return true
    }

// endregion
// region Sticker
/**
 * Добавляет листенер стикера
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
fun BotCreator.addStickerListener(
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
    val answerTGFileIn = answerTGFile?.convertTgFileToString()
    stickers.add(
        StickerTG(
            createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
    )
}

internal fun BotCreator.addSticker(dispatcher: Dispatcher, com: ListenerTgBase): Boolean =
    with(dispatcher) {
        sticker {
            answerListener(com, this.bot, this@with, message)
        }
        return true
    }

// endregion
// region Voice
/**
 * Добавляет листенер голосового сообщения
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
fun BotCreator.addVoicesListener(
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
    val answerTGFileIn = answerTGFile?.convertTgFileToString()
    voices.add(
        VoiceTG(
            createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText,
                answerTGFileIn,
                answerTGFile?.convertFromTgType(),
                lat,
                lon,
                question,
                pollList,
                title,
                address,
                phoneNumber,
                firstName
            )
        )
    )
}

internal fun BotCreator.addVoices(dispatcher: Dispatcher, com: ListenerTgBase): Boolean =
    with(dispatcher) {
        voice {
            answerListener(com, this.bot, this@with, message)
        }
        return true
    }

// endregion
// region VideoNote
/**
 * Добавляет листенер видео кружочка
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
fun BotCreator.addVideoNotesListener(
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
    val answerTGFileIn = answerTGFile?.convertTgFileToString()
    videoNotes.add(
        VideoNoteTG(
            createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText,
                answerTGFileIn,
                answerTGFile?.convertFromTgType(),
                lat,
                lon,
                question,
                pollList,
                title,
                address,
                phoneNumber,
                firstName
            )
        )
    )
}

internal fun BotCreator.addVideoNotes(dispatcher: Dispatcher, com: ListenerTgBase): Boolean =
    with(dispatcher) {
        videoNote {
            answerListener(com, this.bot, this@with, message)
        }
        return true
    }

// endregion
// region Videos
/**
 * Добавляет листенер видео
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
fun BotCreator.addVideosListener(
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
    val answerTGFileIn = answerTGFile?.convertTgFileToString()
    videos.add(
        VideoTG(
            createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText,
                answerTGFileIn,
                answerTGFile?.convertFromTgType(),
                lat,
                lon,
                question,
                pollList,
                title,
                address,
                phoneNumber,
                firstName
            )
        )
    )
}

internal fun BotCreator.addVideos(dispatcher: Dispatcher, com: ListenerTgBase): Boolean =
    with(dispatcher) {
        video {
            answerListener(com, this.bot, this@with, message)
        }
        return true
    }

// endregion
// region Photos
/**
 * Добавляет листенер изображения
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
fun BotCreator.addPhotosListener(
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
    val answerTGFileIn = answerTGFile?.convertTgFileToString()
    photos.add(
        PhotoTG(
            createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText,
                answerTGFileIn,
                answerTGFile?.convertFromTgType(),
                lat,
                lon,
                question,
                pollList,
                title,
                address,
                phoneNumber,
                firstName
            )
        )
    )
}

internal fun BotCreator.addPhotos(dispatcher: Dispatcher, com: ListenerTgBase): Boolean =
    with(dispatcher) {
        photos {
            answerListener(com, this.bot, this@with, message)
        }
        return true
    }

// endregion
// region Locations
/**
 * Добавляет листенер локации
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
fun BotCreator.addLocationsListener(
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
    val answerTGFileIn = answerTGFile?.convertTgFileToString()
    locations.add(
        LocationTG(
            createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText,
                answerTGFileIn,
                answerTGFile?.convertFromTgType(),
                lat,
                lon,
                question,
                pollList,
                title,
                address,
                phoneNumber,
                firstName
            )
        )
    )
}

internal fun BotCreator.addLocations(dispatcher: Dispatcher, com: ListenerTgBase): Boolean =
    with(dispatcher) {
        location {
            answerListener(com, this.bot, this@with, message)
        }
        return true
    }

// endregion
// region Contact
/**
 * Добавляет листенер контакта
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
fun BotCreator.addContactsListener(
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
    val answerTGFileIn = answerTGFile?.convertTgFileToString()
    contacts.add(
        ContactTG(
            createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText,
                answerTGFileIn,
                answerTGFile?.convertFromTgType(),
                lat,
                lon,
                question,
                pollList,
                title,
                address,
                phoneNumber,
                firstName
            )
        )
    )
}

internal fun BotCreator.addContacts(dispatcher: Dispatcher, com: ListenerTgBase): Boolean =
    with(dispatcher) {
        contact {
            answerListener(com, this.bot, this@with, message)
        }
        return true
    }

// endregion
