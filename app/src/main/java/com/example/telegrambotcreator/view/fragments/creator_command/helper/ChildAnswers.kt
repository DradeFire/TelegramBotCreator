@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.telegrambotcreator.view.fragments.creator_command.helper

import android.os.Build
import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.helper.*
import com.example.telegrambotcreator.view.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodAnimation
import com.example.telegrambotcreator.view.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodAudio
import com.example.telegrambotcreator.view.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodContact
import com.example.telegrambotcreator.view.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodDocument
import com.example.telegrambotcreator.view.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodLocation
import com.example.telegrambotcreator.view.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodPhoto
import com.example.telegrambotcreator.view.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodPoll
import com.example.telegrambotcreator.view.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodText
import com.example.telegrambotcreator.view.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodVenue
import com.example.telegrambotcreator.view.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodVideo
import com.example.telegrambotcreator.view.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodVoice
import com.example.telegrambotcreator.view.fragments.creator_command.CreatorCommandFragment
import com.github.kotlintelegrambot.entities.TelegramFile
import java.io.File

// region Child Answers

internal fun CreatorCommandFragment.child_inlineAnswer(){
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addInlineButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString(),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addInlineButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString()),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addInlineButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addInlineButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addInlineButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addInlineButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addInlineButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addInlineButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString(),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addInlineButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(bindingCreatorCommandFragment, requireContext()))
                return
            val pollList = (bindingCreatorCommandFragment.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModelCreatorCommandFragment.chosenBot.addInlineButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList,
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addInlineButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                title = bindingCreatorCommandFragment.inputTitle.text.toString(),
                address = bindingCreatorCommandFragment.inputAddress.text.toString(),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addInlineButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_replyAnswer(){
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addReplyButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString(),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addReplyButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString()),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addReplyButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addReplyButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addReplyButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addReplyButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addReplyButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addReplyButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString(),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addReplyButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(bindingCreatorCommandFragment, requireContext()))
                return
            val pollList = (bindingCreatorCommandFragment.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModelCreatorCommandFragment.chosenBot.addReplyButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList,
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addReplyButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                title = bindingCreatorCommandFragment.inputTitle.text.toString(),
                address = bindingCreatorCommandFragment.inputAddress.text.toString(),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addReplyButtonToMessage(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = bindingCreatorCommandFragment.inputCommand.text.toString()
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_stickerAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildStickerListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildStickerListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildStickerListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildStickerListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildStickerListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildStickerListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildStickerListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildStickerListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildStickerListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(bindingCreatorCommandFragment, requireContext()))
                return
            val pollList = (bindingCreatorCommandFragment.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModelCreatorCommandFragment.chosenBot.addChildStickerListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildStickerListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                title = bindingCreatorCommandFragment.inputTitle.text.toString(),
                address = bindingCreatorCommandFragment.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildStickerListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_locationAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildLocationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildLocationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildLocationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildLocationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildLocationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildLocationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildLocationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildLocationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildLocationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(bindingCreatorCommandFragment, requireContext()))
                return
            val pollList = (bindingCreatorCommandFragment.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModelCreatorCommandFragment.chosenBot.addChildLocationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildLocationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                title = bindingCreatorCommandFragment.inputTitle.text.toString(),
                address = bindingCreatorCommandFragment.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildLocationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_contactAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildContactListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildContactListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildContactListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildContactListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildContactListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildContactListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildContactListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildContactListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildContactListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(bindingCreatorCommandFragment, requireContext()))
                return
            val pollList = (bindingCreatorCommandFragment.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModelCreatorCommandFragment.chosenBot.addChildContactListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildContactListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                title = bindingCreatorCommandFragment.inputTitle.text.toString(),
                address = bindingCreatorCommandFragment.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildContactListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_voiceAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVoiceListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVoiceListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVoiceListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVoiceListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVoiceListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVoiceListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVoiceListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVoiceListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVoiceListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(bindingCreatorCommandFragment, requireContext()))
                return
            val pollList = (bindingCreatorCommandFragment.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModelCreatorCommandFragment.chosenBot.addChildVoiceListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVoiceListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                title = bindingCreatorCommandFragment.inputTitle.text.toString(),
                address = bindingCreatorCommandFragment.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVoiceListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_videoAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(bindingCreatorCommandFragment, requireContext()))
                return
            val pollList = (bindingCreatorCommandFragment.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModelCreatorCommandFragment.chosenBot.addChildVideoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                title = bindingCreatorCommandFragment.inputTitle.text.toString(),
                address = bindingCreatorCommandFragment.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_photoAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildPhotoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildPhotoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildPhotoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildPhotoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildPhotoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildPhotoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildPhotoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildPhotoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildPhotoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(bindingCreatorCommandFragment, requireContext()))
                return
            val pollList = (bindingCreatorCommandFragment.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModelCreatorCommandFragment.chosenBot.addChildPhotoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildPhotoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                title = bindingCreatorCommandFragment.inputTitle.text.toString(),
                address = bindingCreatorCommandFragment.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildPhotoListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_documentAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildDocumentListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildDocumentListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildDocumentListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildDocumentListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildDocumentListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildDocumentListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildDocumentListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildDocumentListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildDocumentListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(bindingCreatorCommandFragment, requireContext()))
                return
            val pollList = (bindingCreatorCommandFragment.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModelCreatorCommandFragment.chosenBot.addChildDocumentListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildDocumentListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                title = bindingCreatorCommandFragment.inputTitle.text.toString(),
                address = bindingCreatorCommandFragment.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildDocumentListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_animationAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildAnimationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildAnimationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildAnimationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildAnimationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildAnimationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildAnimationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildAnimationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildAnimationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildAnimationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(bindingCreatorCommandFragment, requireContext()))
                return
            val pollList = (bindingCreatorCommandFragment.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModelCreatorCommandFragment.chosenBot.addChildAnimationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildAnimationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                title = bindingCreatorCommandFragment.inputTitle.text.toString(),
                address = bindingCreatorCommandFragment.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildAnimationListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_textAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(bindingCreatorCommandFragment, requireContext()))
                return
            val pollList = (bindingCreatorCommandFragment.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModelCreatorCommandFragment.chosenBot.addChildTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                title = bindingCreatorCommandFragment.inputTitle.text.toString(),
                address = bindingCreatorCommandFragment.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_commandAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(bindingCreatorCommandFragment, requireContext()))
                return
            val pollList = (bindingCreatorCommandFragment.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModelCreatorCommandFragment.chosenBot.addChildCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                title = bindingCreatorCommandFragment.inputTitle.text.toString(),
                address = bindingCreatorCommandFragment.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_videoNoteAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoNoteListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoNoteListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoNoteListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoNoteListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoNoteListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoNoteListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoNoteListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoNoteListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoNoteListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(bindingCreatorCommandFragment, requireContext()))
                return
            val pollList = (bindingCreatorCommandFragment.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModelCreatorCommandFragment.chosenBot.addChildVideoNoteListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoNoteListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = bindingCreatorCommandFragment.inputLat.text.toString().toFloat(),
                lon = bindingCreatorCommandFragment.inputLon.text.toString().toFloat(),
                title = bindingCreatorCommandFragment.inputTitle.text.toString(),
                address = bindingCreatorCommandFragment.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addChildVideoNoteListener(
                fatherId = viewModelCreatorCommandFragment.commandsDeque.peek().id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
// endregion