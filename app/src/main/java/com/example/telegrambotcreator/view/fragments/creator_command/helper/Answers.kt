package com.example.telegrambotcreator.view.fragments.creator_command.helper

import android.os.Build
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
import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.helper.*
import com.example.telegrambotcreator.view.fragments.creator_command.CreatorCommandFragment
import com.github.kotlintelegrambot.entities.TelegramFile
import java.io.File

// region Answers

internal fun CreatorCommandFragment.stickerAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addStickerListener(
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
            viewModelCreatorCommandFragment.chosenBot.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addStickerListener(
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
            viewModelCreatorCommandFragment.chosenBot.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.videoNoteAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideoNotesListener(
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
            viewModelCreatorCommandFragment.chosenBot.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideoNotesListener(
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
            viewModelCreatorCommandFragment.chosenBot.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.locationAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addLocationsListener(
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
            viewModelCreatorCommandFragment.chosenBot.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addLocationsListener(
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
            viewModelCreatorCommandFragment.chosenBot.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.contactAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addContactsListener(
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
            viewModelCreatorCommandFragment.chosenBot.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addContactsListener(
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
            viewModelCreatorCommandFragment.chosenBot.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.voiceAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVoicesListener(
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
            viewModelCreatorCommandFragment.chosenBot.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVoicesListener(
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
            viewModelCreatorCommandFragment.chosenBot.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.videoAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideosListener(
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
            viewModelCreatorCommandFragment.chosenBot.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addVideosListener(
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
            viewModelCreatorCommandFragment.chosenBot.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.photoAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addPhotosListener(
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
            viewModelCreatorCommandFragment.chosenBot.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addPhotosListener(
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
            viewModelCreatorCommandFragment.chosenBot.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.documentAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addDocumentListener(
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
            viewModelCreatorCommandFragment.chosenBot.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addDocumentListener(
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
            viewModelCreatorCommandFragment.chosenBot.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.animationAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addAnimationListener(
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
            viewModelCreatorCommandFragment.chosenBot.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addAnimationListener(
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
            viewModelCreatorCommandFragment.chosenBot.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.commandAnswer(){
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
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
            viewModelCreatorCommandFragment.chosenBot.addCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
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
            viewModelCreatorCommandFragment.chosenBot.addCommandListener(
                command = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
internal fun CreatorCommandFragment.textAnswer() {
    when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
        BotCreator.TypeAnswer.TEXT -> {
            if(isNotGoodText(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = bindingCreatorCommandFragment.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if(isNotGoodAnimation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(bindingCreatorCommandFragment.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if(isNotGoodAudio(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if(isNotGoodDocument(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if(isNotGoodPhoto(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if(isNotGoodVideo(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if(isNotGoodVoice(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if(isNotGoodContact(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = bindingCreatorCommandFragment.inputPhone.text.toString(),
                firstName = bindingCreatorCommandFragment.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if(isNotGoodLocation(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
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
            viewModelCreatorCommandFragment.chosenBot.addTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = bindingCreatorCommandFragment.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if(isNotGoodVenue(bindingCreatorCommandFragment, requireContext()))
                return
            viewModelCreatorCommandFragment.chosenBot.addTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
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
            viewModelCreatorCommandFragment.chosenBot.addTextListener(
                text = bindingCreatorCommandFragment.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
// endregion