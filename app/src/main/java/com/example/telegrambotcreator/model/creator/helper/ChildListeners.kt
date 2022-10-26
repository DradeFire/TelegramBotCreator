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
        val obj = CommandTG(createNewID(), fatherId, command, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

        if (baseTgContainer.inCommand == null)
            baseTgContainer.inCommand = arrayListOf(obj)
        else
            (baseTgContainer.inCommand as ArrayList<CommandTG>).add(obj)
    }
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
        val obj = TextTG(createNewID(), fatherId, text, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

        if (baseTgContainer.inText == null)
            baseTgContainer.inText = arrayListOf(obj)
        else
            (baseTgContainer.inText as ArrayList<TextTG>).add(obj)
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
        val obj = AnimationTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

        if (baseTgContainer.inAnimation == null)
            baseTgContainer.inAnimation = arrayListOf(obj)
        else
            (baseTgContainer.inAnimation as ArrayList<AnimationTG>).add(obj)
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
        val obj = DocumentTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

        if (baseTgContainer.inDocument == null)
            baseTgContainer.inDocument = arrayListOf(obj)
        else
            (baseTgContainer.inDocument as ArrayList<DocumentTG>).add(obj)
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
        val obj = StickerTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

        if (baseTgContainer.inSticker == null)
            baseTgContainer.inSticker = arrayListOf(obj)
        else
            (baseTgContainer.inSticker as ArrayList<StickerTG>).add(obj)
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
        val obj = VoiceTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

        if (baseTgContainer.inVoice == null)
            baseTgContainer.inVoice = arrayListOf(obj)
        else
            (baseTgContainer.inVoice as ArrayList<VoiceTG>).add(obj)
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
        val obj = VideoNoteTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

        if (baseTgContainer.inVideoNote == null)
            baseTgContainer.inVideoNote = arrayListOf(obj)
        else
            (baseTgContainer.inVideoNote as ArrayList<VideoNoteTG>).add(obj)
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
        val obj = VideoTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

        if (baseTgContainer.inVideo == null)
            baseTgContainer.inVideo = arrayListOf(obj)
        else
            (baseTgContainer.inVideo as ArrayList<VideoTG>).add(obj)
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
        val obj = PhotoTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

        if (baseTgContainer.inPhoto == null)
            baseTgContainer.inPhoto = arrayListOf(obj)
        else
            (baseTgContainer.inPhoto as ArrayList<PhotoTG>).add(obj)
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
        val obj = LocationTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

        if (baseTgContainer.inLocation == null)
            baseTgContainer.inLocation = arrayListOf(obj)
        else
            (baseTgContainer.inLocation as ArrayList<LocationTG>).add(obj)
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
        val obj = ContactTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

        if (baseTgContainer.inContact == null)
            baseTgContainer.inContact = arrayListOf(obj)
        else
            (baseTgContainer.inContact as ArrayList<ContactTG>).add(obj)
    }
}
