package com.example.telegrambotcreator.model.creator.helper

import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.model.*
import com.github.kotlintelegrambot.entities.TelegramFile

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
    val answerTGFileIn = when(answerTGFile){
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = CommandTG(createNewID(), command, typeAnswer.convertFromType(), fatherId,
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
    val answerTGFileIn = when(answerTGFile){
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = TextTG(createNewID(), text, typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

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
    val answerTGFileIn = when(answerTGFile){
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = AnimationTG(createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

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
    val answerTGFileIn = when(answerTGFile){
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = DocumentTG(createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

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
    val answerTGFileIn = when(answerTGFile){
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = StickerTG(createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

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
    val answerTGFileIn = when(answerTGFile){
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = VoiceTG(createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

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
    val answerTGFileIn = when(answerTGFile){
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = VideoNoteTG(createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

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
    val answerTGFileIn = when(answerTGFile){
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = VideoTG(createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

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
    val answerTGFileIn = when(answerTGFile){
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.path
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = PhotoTG(createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

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
    val answerTGFileIn = when(answerTGFile){
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = LocationTG(createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}

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
    val answerTGFileIn = when(answerTGFile){
        is TelegramFile.ByUrl -> answerTGFile.url
        is TelegramFile.ByFile -> answerTGFile.file.absolutePath
        is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
        is TelegramFile.ByFileId -> answerTGFile.fileId
        else -> null
    }
    val father = findFather(fatherId ?: -1)
    father?.let { baseTgContainer ->
        val obj = ContactTG(createNewID(), typeAnswer.convertFromType(), fatherId,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName
            )
        )
        addingNewListeners(baseTgContainer, obj)
    }
}
