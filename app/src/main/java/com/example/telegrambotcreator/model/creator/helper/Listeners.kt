package com.example.telegrambotcreator.model.creator.helper

import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.model.*
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.entities.KeyboardReplyMarkup
import com.github.kotlintelegrambot.entities.TelegramFile

    // region All Listeners
    // region Command

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
        val answerTGFileIn = when(answerTGFile){
            is TelegramFile.ByUrl -> answerTGFile.url
            is TelegramFile.ByFile -> answerTGFile.file.absolutePath
            is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
            is TelegramFile.ByFileId -> answerTGFile.fileId
            else -> null
        }
        commands.add(CommandTG(createNewID(), null, command, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName))
    }
    fun BotCreator.addChildCommandListener(
        command: String,
        fatherId: Int,
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
        val father = findFather(fatherId)
        father?.let { baseTgContainer ->
            val obj = CommandTG(createNewID(), fatherId, command, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

            if (baseTgContainer.inCommand == null)
                baseTgContainer.inCommand = arrayListOf(obj)
            else
                (baseTgContainer.inCommand as ArrayList<CommandTG>).add(obj)
        }
    }

    internal fun BotCreator.addCommands(dispatcher: Dispatcher, commands: List<CommandTG>): Boolean = with(dispatcher) {
        commands.forEach { com ->
            command(com.command) {
                KeyboardReplyMarkup().keyboard
                answerListener(com, this.bot, this@with, message)
            }
        }
        return true
    }
    // endregion
    // region Text
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
        val answerTGFileIn = when(answerTGFile){
            is TelegramFile.ByUrl -> answerTGFile.url
            is TelegramFile.ByFile -> answerTGFile.file.absolutePath
            is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
            is TelegramFile.ByFileId -> answerTGFile.fileId
            else -> null
        }
        texts.add(TextTG(createNewID(), null, text, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName))
    }
    fun BotCreator.addChildTextListener(
        text: String,
        fatherId: Int,
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
        val father = findFather(fatherId)
        father?.let { baseTgContainer ->
            val obj = TextTG(createNewID(), fatherId, text, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

            if (baseTgContainer.inText == null)
                baseTgContainer.inText = arrayListOf(obj)
            else
                (baseTgContainer.inText as ArrayList<TextTG>).add(obj)
        }
    }
    internal fun BotCreator.addText(dispatcher: Dispatcher, commands: List<TextTG>): Boolean = with(dispatcher) {
        commands.forEach { com ->
            text(com.text) {
                answerListener(com, this.bot, this@with, message)
            }
        }
        return true
    }
    // endregion
    // region Animation
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
        val answerTGFileIn = when(answerTGFile){
            is TelegramFile.ByUrl -> answerTGFile.url
            is TelegramFile.ByFile -> answerTGFile.file.absolutePath
            is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
            is TelegramFile.ByFileId -> answerTGFile.fileId
            else -> null
        }
        animations.add(AnimationTG(createNewID(), null, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName))
    }
    fun BotCreator.addChildAnimationListener(
        fatherId: Int,
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
        val father = findFather(fatherId)
        father?.let { baseTgContainer ->
            val obj = AnimationTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

            if (baseTgContainer.inAnimation == null)
                baseTgContainer.inAnimation = arrayListOf(obj)
            else
                (baseTgContainer.inAnimation as ArrayList<AnimationTG>).add(obj)
        }
    }
internal fun BotCreator.addAnimation(dispatcher: Dispatcher, commands: List<AnimationTG>): Boolean = with(dispatcher)  {
        commands.forEach { com ->
            animation {
                answerListener(com, this.bot, this@with, message)
            }
        }
        return true
    }
    // endregion
    // region Document
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
        val answerTGFileIn = when(answerTGFile){
            is TelegramFile.ByUrl -> answerTGFile.url
            is TelegramFile.ByFile -> answerTGFile.file.absolutePath
            is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
            is TelegramFile.ByFileId -> answerTGFile.fileId
            else -> null
        }
        documents.add(DocumentTG(createNewID(), null, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName))
    }
    fun BotCreator.addChildDocumentListener(
        fatherId: Int,
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
        val father = findFather(fatherId)
        father?.let { baseTgContainer ->
            val obj = DocumentTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

            if (baseTgContainer.inDocument == null)
                baseTgContainer.inDocument = arrayListOf(obj)
            else
                (baseTgContainer.inDocument as ArrayList<DocumentTG>).add(obj)
        }
    }
    internal fun BotCreator.addDocument(dispatcher: Dispatcher, commands: List<DocumentTG>): Boolean = with(dispatcher)  {
        commands.forEach { com ->
            document {
                answerListener(com, this.bot,  this@with, message)
            }
        }
        return true
    }
    // endregion
    // region Sticker
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
        val answerTGFileIn = when(answerTGFile){
            is TelegramFile.ByUrl -> answerTGFile.url
            is TelegramFile.ByFile -> answerTGFile.file.absolutePath
            is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
            is TelegramFile.ByFileId -> answerTGFile.fileId
            else -> null
        }
        stickers.add(StickerTG(createNewID(), null, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName))
    }
    fun BotCreator.addChildStickerListener(
        fatherId: Int,
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
        val father = findFather(fatherId)
        father?.let { baseTgContainer ->
            val obj = StickerTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

            if (baseTgContainer.inSticker == null)
                baseTgContainer.inSticker = arrayListOf(obj)
            else
                (baseTgContainer.inSticker as ArrayList<StickerTG>).add(obj)
        }
    }
    internal fun BotCreator.addSticker(dispatcher: Dispatcher, commands: List<StickerTG>): Boolean = with(dispatcher)  {
        commands.forEach { com ->
            sticker {
                answerListener(com, this.bot, this@with, message)
            }
        }
        return true
    }
    // endregion
    // region Voice
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
        val answerTGFileIn = when(answerTGFile){
            is TelegramFile.ByUrl -> answerTGFile.url
            is TelegramFile.ByFile -> answerTGFile.file.absolutePath
            is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
            is TelegramFile.ByFileId -> answerTGFile.fileId
            else -> null
        }
        voices.add(VoiceTG(createNewID(), null, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName))
    }
    fun BotCreator.addChildVoiceListener(
        fatherId: Int,
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
        val father = findFather(fatherId)
        father?.let { baseTgContainer ->
            val obj = VoiceTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

            if (baseTgContainer.inVoice == null)
                baseTgContainer.inVoice = arrayListOf(obj)
            else
                (baseTgContainer.inVoice as ArrayList<VoiceTG>).add(obj)
        }
    }
    internal fun BotCreator.addVoices(dispatcher: Dispatcher, commands: List<VoiceTG>): Boolean = with(dispatcher)  {
        commands.forEach { com ->
            voice {
                answerListener(com, this.bot,  this@with, message)
            }
        }
        return true
    }
    // endregion
    // region VideoNote
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
        val answerTGFileIn = when(answerTGFile){
            is TelegramFile.ByUrl -> answerTGFile.url
            is TelegramFile.ByFile -> answerTGFile.file.absolutePath
            is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
            is TelegramFile.ByFileId -> answerTGFile.fileId
            else -> null
        }
        videoNotes.add(VideoNoteTG(createNewID(), null, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName))
    }
    fun BotCreator.addChildVideoNoteListener(
        fatherId: Int,
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
        val father = findFather(fatherId)
        father?.let { baseTgContainer ->
            val obj = VideoNoteTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

            if (baseTgContainer.inVideoNote == null)
                baseTgContainer.inVideoNote = arrayListOf(obj)
            else
                (baseTgContainer.inVideoNote as ArrayList<VideoNoteTG>).add(obj)
        }
    }
    internal fun BotCreator.addVideoNotes(dispatcher: Dispatcher, commands: List<VideoNoteTG>): Boolean = with(dispatcher)  {
        commands.forEach { com ->
            videoNote {
                answerListener(com, this.bot,  this@with, message)
            }
        }
        return true
    }
    // endregion
    // region Videos
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
        val answerTGFileIn = when(answerTGFile){
            is TelegramFile.ByUrl -> answerTGFile.url
            is TelegramFile.ByFile -> answerTGFile.file.absolutePath
            is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
            is TelegramFile.ByFileId -> answerTGFile.fileId
            else -> null
        }
        videos.add(VideoTG(createNewID(), null, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName))
    }
    fun BotCreator.addChildVideoListener(
        fatherId: Int,
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
        val father = findFather(fatherId)
        father?.let { baseTgContainer ->
            val obj = VideoTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

            if (baseTgContainer.inVideo == null)
                baseTgContainer.inVideo = arrayListOf(obj)
            else
                (baseTgContainer.inVideo as ArrayList<VideoTG>).add(obj)
        }
    }
    internal fun BotCreator.addVideos(dispatcher: Dispatcher, commands: List<VideoTG>): Boolean = with(dispatcher)  {
        commands.forEach { com ->
            video {
                answerListener(com, this.bot,  this@with, message)
            }
        }
        return true
    }
    // endregion
    // region Photos
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
        val answerTGFileIn = when(answerTGFile){
            is TelegramFile.ByUrl -> answerTGFile.url
            is TelegramFile.ByFile -> answerTGFile.file.path
            is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
            is TelegramFile.ByFileId -> answerTGFile.fileId
            else -> null
        }
        photos.add(PhotoTG(createNewID(), null, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName))
    }
    fun BotCreator.addChildPhotoListener(
        fatherId: Int,
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
        val father = findFather(fatherId)
        father?.let { baseTgContainer ->
            val obj = PhotoTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

            if (baseTgContainer.inPhoto == null)
                baseTgContainer.inPhoto = arrayListOf(obj)
            else
                (baseTgContainer.inPhoto as ArrayList<PhotoTG>).add(obj)
        }
    }
    internal fun BotCreator.addPhotos(dispatcher: Dispatcher, commands: List<PhotoTG>): Boolean = with(dispatcher)  {
        commands.forEach { com ->
            photos {
                answerListener(com, this.bot,  this@with, message)
            }
        }
        return true
    }
    // endregion
    // region Locations
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
        val answerTGFileIn = when(answerTGFile){
            is TelegramFile.ByUrl -> answerTGFile.url
            is TelegramFile.ByFile -> answerTGFile.file.absolutePath
            is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
            is TelegramFile.ByFileId -> answerTGFile.fileId
            else -> null
        }
        locations.add(LocationTG(createNewID(), null, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName))
    }
    fun BotCreator.addChildLocationListener(
        fatherId: Int,
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
        val father = findFather(fatherId)
        father?.let { baseTgContainer ->
            val obj = LocationTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

            if (baseTgContainer.inLocation == null)
                baseTgContainer.inLocation = arrayListOf(obj)
            else
                (baseTgContainer.inLocation as ArrayList<LocationTG>).add(obj)
        }
    }
    internal fun BotCreator.addLocations(dispatcher: Dispatcher, commands: List<LocationTG>): Boolean {
        with(dispatcher) {
            commands.forEach { com ->
                location {
                    answerListener(com, this.bot,  this@with, message)
                }
            }
        }
        return true
    }
    // endregion
    // region Contact
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
        val answerTGFileIn = when(answerTGFile){
            is TelegramFile.ByUrl -> answerTGFile.url
            is TelegramFile.ByFile -> answerTGFile.file.absolutePath
            is TelegramFile.ByByteArray -> answerTGFile.fileBytes.toString()
            is TelegramFile.ByFileId -> answerTGFile.fileId
            else -> null
        }
        contacts.add(ContactTG(createNewID(), null, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName))
    }
    fun BotCreator.addChildContactListener(
        fatherId: Int,
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
        val father = findFather(fatherId)
        father?.let { baseTgContainer ->
            val obj = ContactTG(createNewID(), fatherId, typeAnswer.convertFromType(), answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)

            if (baseTgContainer.inContact == null)
                baseTgContainer.inContact = arrayListOf(obj)
            else
                (baseTgContainer.inContact as ArrayList<ContactTG>).add(obj)
        }
    }
    internal fun BotCreator.addContacts(dispatcher: Dispatcher, commands: List<ContactTG>): Boolean {
        with(dispatcher) {
            commands.forEach { com ->
                contact {
                    answerListener(com, this.bot,  this@with, message)
                }
            }
        }
        return true
    }
    // endregion
    //endregion