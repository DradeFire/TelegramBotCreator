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

internal fun CreatorCommandFragment.child_inlineAnswer() = with(binding!!){
    when(this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addInlineButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString(),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addInlineButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString()),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addInlineButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addInlineButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addInlineButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addInlineButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addInlineButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addInlineButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString(),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addInlineButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addInlineButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList,
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addInlineButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString(),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addInlineButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = this.inputCommand.text.toString()
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_replyAnswer() = with(binding!!){
    when(this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addReplyButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString(),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addReplyButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString()),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addReplyButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addReplyButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addReplyButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addReplyButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addReplyButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addReplyButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString(),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addReplyButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addReplyButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList,
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addReplyButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString(),
                textButton = this.inputCommand.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addReplyButtonToMessage(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc)),
                textButton = this.inputCommand.text.toString()
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_stickerAnswer() = with(binding!!) {
    when(this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildStickerListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildStickerListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildStickerListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildStickerListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildStickerListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildStickerListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildStickerListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildStickerListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildStickerListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addChildStickerListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildStickerListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildStickerListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_locationAnswer() = with(binding!!) {
    when(this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildLocationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildLocationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildLocationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildLocationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildLocationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildLocationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildLocationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildLocationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildLocationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addChildLocationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildLocationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildLocationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_contactAnswer() = with(binding!!) {
    when(this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildContactListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildContactListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildContactListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildContactListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildContactListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildContactListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildContactListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildContactListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildContactListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addChildContactListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildContactListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildContactListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_voiceAnswer() = with(binding!!) {
    when(this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVoiceListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVoiceListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVoiceListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVoiceListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVoiceListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVoiceListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVoiceListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVoiceListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVoiceListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addChildVoiceListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVoiceListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVoiceListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_videoAnswer() = with(binding!!) {
    when(this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addChildVideoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_photoAnswer() = with(binding!!) {
    when(this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildPhotoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildPhotoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildPhotoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildPhotoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildPhotoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildPhotoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildPhotoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildPhotoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildPhotoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addChildPhotoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildPhotoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildPhotoListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_documentAnswer() = with(binding!!) {
    when(this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildDocumentListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildDocumentListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildDocumentListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildDocumentListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildDocumentListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildDocumentListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildDocumentListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildDocumentListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildDocumentListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addChildDocumentListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildDocumentListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildDocumentListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_animationAnswer() = with(binding!!) {
    when(this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildAnimationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildAnimationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildAnimationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildAnimationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildAnimationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildAnimationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildAnimationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildAnimationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildAnimationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addChildAnimationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildAnimationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildAnimationListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_textAnswer() = with(binding!!) {
    when(this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildTextListener(
                text = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildTextListener(
                text = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildTextListener(
                text = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildTextListener(
                text = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildTextListener(
                text = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildTextListener(
                text = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildTextListener(
                text = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildTextListener(
                text = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildTextListener(
                text = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addChildTextListener(
                text = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildTextListener(
                text = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildTextListener(
                text = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_commandAnswer() = with(binding!!) {
    when(this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildCommandListener(
                command = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildCommandListener(
                command = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildCommandListener(
                command = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildCommandListener(
                command = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildCommandListener(
                command = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildCommandListener(
                command = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildCommandListener(
                command = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildCommandListener(
                command = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildCommandListener(
                command = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addChildCommandListener(
                command = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildCommandListener(
                command = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildCommandListener(
                command = this.inputCommand.text.toString(),
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.child_videoNoteAnswer() = with(binding!!) {
    when(this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoNoteListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoNoteListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoNoteListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoNoteListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoNoteListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoNoteListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoNoteListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoNoteListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoNoteListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if(isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if(it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addChildVideoNoteListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoNoteListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if(isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addChildVideoNoteListener(
                fatherId = viewModel?.commandsDeque?.peek()?.id,
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
// endregion