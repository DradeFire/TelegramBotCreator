package com.example.telegrambotcreator.model.creator.helper

import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.model.*
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.entities.TelegramFile

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
        commands.add(
            CommandTG(createNewID(), command, typeAnswer.convertFromType(), null,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)
            )
        )
    }

    internal fun BotCreator.addCommands(dispatcher: Dispatcher, command: CommandTG): Boolean = with(dispatcher) {
        command(command.command) {
            answerListener(command, this.bot, this@with, message)
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
        texts.add(
            TextTG(createNewID(), text, typeAnswer.convertFromType(), null,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)
            )
        )
    }

    internal fun BotCreator.addText(dispatcher: Dispatcher, command: TextTG): Boolean = with(dispatcher) {
        text(command.text) {
            answerListener(command, this.bot, this@with, message)
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
        animations.add(
            AnimationTG(createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)
            )
        )
    }

    internal fun BotCreator.addAnimation(dispatcher: Dispatcher, command: AnimationTG): Boolean = with(dispatcher)  {
        animation {
            answerListener(command, this.bot, this@with, message)
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
        documents.add(
            DocumentTG(createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)
            )
        )
    }

    internal fun BotCreator.addDocument(dispatcher: Dispatcher, com: ListenerTgBase): Boolean = with(dispatcher)  {
        document {
            answerListener(com, this.bot,  this@with, message)
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
        stickers.add(
            StickerTG(createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(),
                lat, lon, question, pollList, title, address, phoneNumber, firstName)
            )
        )
    }

    internal fun BotCreator.addSticker(dispatcher: Dispatcher, com: ListenerTgBase): Boolean = with(dispatcher)  {
        sticker {
            answerListener(com, this.bot, this@with, message)
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
        voices.add(
            VoiceTG(createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)
            )
        )
    }

    internal fun BotCreator.addVoices(dispatcher: Dispatcher, com: ListenerTgBase): Boolean = with(dispatcher)  {
        voice {
            answerListener(com, this.bot,  this@with, message)
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
        videoNotes.add(
            VideoNoteTG(createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                    answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)
            )
        )
    }

    internal fun BotCreator.addVideoNotes(dispatcher: Dispatcher, com: ListenerTgBase): Boolean = with(dispatcher)  {
        videoNote {
            answerListener(com, this.bot,  this@with, message)
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
        videos.add(
            VideoTG(createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                    answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)
            )
        )
    }

    internal fun BotCreator.addVideos(dispatcher: Dispatcher, com: ListenerTgBase): Boolean = with(dispatcher)  {
        video {
            answerListener(com, this.bot,  this@with, message)
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
        photos.add(
            PhotoTG(createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)
            )
        )
    }

    internal fun BotCreator.addPhotos(dispatcher: Dispatcher, com: ListenerTgBase): Boolean = with(dispatcher)  {
        photos {
            answerListener(com, this.bot,  this@with, message)
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
        locations.add(
            LocationTG(createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)
            )
        )
    }
    internal fun BotCreator.addLocations(dispatcher: Dispatcher, com: ListenerTgBase): Boolean = with(dispatcher) {
        location {
            answerListener(com, this.bot, this@with, message)
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
        contacts.add(
            ContactTG(createNewID(), typeAnswer.convertFromType(), null,
            ActionTG(
                answerText, answerTGFileIn, answerTGFile?.convertFromTgType(), lat, lon, question, pollList, title, address, phoneNumber, firstName)
            )
        )
    }
    internal fun BotCreator.addContacts(dispatcher: Dispatcher, com: ListenerTgBase): Boolean = with(dispatcher) {
        contact {
            answerListener(com, this.bot,  this@with, message)
        }
        return true
    }
    // endregion
