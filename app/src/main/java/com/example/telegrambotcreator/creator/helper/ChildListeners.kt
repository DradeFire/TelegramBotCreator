package com.example.telegrambotcreator.creator.helper

import com.example.telegrambotcreator.creator.BotCreator
import com.example.telegrambotcreator.creator.model.*
import com.example.telegrambotcreator.creator.model.ifelse.IfElseTG
import com.example.telegrambotcreator.creator.model.listeners.*
import com.github.kotlintelegrambot.entities.TelegramFile

/**
 * Добавляет дочерний листенер команд
 * @param command Команда листенера, для пользователя буде выглядеть "/команда"
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
fun BotCreator.addChildCommandListener(
    command: String,
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
    val answerTGFileIn = when (answerTGFile) {
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = CommandTG(
            createNewID(), command, typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

/**
 * Добавляет дочерний листенер обычного текста
 * @param text Команда листенера, для пользователя буде выглядеть "команда"
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
fun BotCreator.addChildTextListener(
    text: String,
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
    val answerTGFileIn = when (answerTGFile) {
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = TextTG(
            createNewID(), text, typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

/**
 * Добавляет дочерний листенер анимации
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
fun BotCreator.addChildAnimationListener(
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
    val answerTGFileIn = when (answerTGFile) {
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = AnimationTG(
            createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

/**
 * Добавляет дочерний листенер документа
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
fun BotCreator.addChildDocumentListener(
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
    val answerTGFileIn = when (answerTGFile) {
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = DocumentTG(
            createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

/**
 * Добавляет дочерний листенер стикера
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
fun BotCreator.addChildStickerListener(
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
    val answerTGFileIn = when (answerTGFile) {
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = StickerTG(
            createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

/**
 * Добавляет дочерний листенер голосового сообщения
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
fun BotCreator.addChildVoiceListener(
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
    val answerTGFileIn = when (answerTGFile) {
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = VoiceTG(
            createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

/**
 * Добавляет дочерний листенер видео кружочка
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
fun BotCreator.addChildVideoNoteListener(
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
    val answerTGFileIn = when (answerTGFile) {
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = VideoNoteTG(
            createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

/**
 * Добавляет дочерний листенер видео
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
fun BotCreator.addChildVideoListener(
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
    val answerTGFileIn = when (answerTGFile) {
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = VideoTG(
            createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

/**
 * Добавляет дочерний листенер изображения
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
fun BotCreator.addChildPhotoListener(
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
    val answerTGFileIn = when (answerTGFile) {
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.path
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = PhotoTG(
            createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

/**
 * Добавляет дочерний листенер локации
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
fun BotCreator.addChildLocationListener(
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
    val answerTGFileIn = when (answerTGFile) {
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = LocationTG(
            createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

/**
 * Добавляет дочерний листенер контакта
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
fun BotCreator.addChildContactListener(
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
    val answerTGFileIn = when (answerTGFile) {
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = ContactTG(
            createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

fun addingNewListeners(baseTgContainer: ListenerTgBase, obj: ListenerTgBase) {
    if (baseTgContainer.inListeners == null)
        baseTgContainer.inListeners = arrayListOf(obj)
    else
        (baseTgContainer.inListeners as ArrayList<ListenerTgBase>).add(obj)
}

/**
 * #Тестируется#
 */
fun BotCreator.addIfElseListener(
    obj: ListenerTgBase,
    value: String,
    isInt: Boolean,
    operation: CompareOperation<Comparable<*>>,
    actionTG: ActionTG,
    elseActionTG: ActionTG
) = apply {
    val answerTGFileIn = when (val type =
        obj.action?.answerTGFile?.convertToTgType(obj?.action?.tgFileType.toString())) {
        is TelegramFile.ByUrl -> type.url
        is TelegramFile.ByFile -> type.file.absolutePath
        is TelegramFile.ByByteArray -> type.fileBytes.toString()
        is TelegramFile.ByFileId -> type.fileId
        else -> null
    }
    val father = findFather(obj.fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = IfElseTG(
            if (isInt) value.toInt() else value, operation,
            actionTG, elseActionTG,
        )

        if ((baseTgContainer as TextTG).inIfElse == null)
            (baseTgContainer).inIfElse = arrayListOf(obj)
        else
            (baseTgContainer.inListeners as ArrayList<IfElseTG>).add(obj)
    }
}

