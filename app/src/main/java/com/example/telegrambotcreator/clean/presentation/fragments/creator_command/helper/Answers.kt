package com.example.telegrambotcreator.clean.presentation.fragments.creator_command.helper

import android.os.Build
import com.example.telegrambotcreator.databinding.FragmentCreatorCommandBinding
import com.example.telegrambotcreator.clean.presentation.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodAnimation
import com.example.telegrambotcreator.clean.presentation.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodAudio
import com.example.telegrambotcreator.clean.presentation.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodContact
import com.example.telegrambotcreator.clean.presentation.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodDocument
import com.example.telegrambotcreator.clean.presentation.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodLocation
import com.example.telegrambotcreator.clean.presentation.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodPhoto
import com.example.telegrambotcreator.clean.presentation.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodPoll
import com.example.telegrambotcreator.clean.presentation.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodText
import com.example.telegrambotcreator.clean.presentation.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodVenue
import com.example.telegrambotcreator.clean.presentation.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodVideo
import com.example.telegrambotcreator.clean.presentation.fragments.creator_command.helper.IsNotGoodFunctions.isNotGoodVoice
import com.example.telegrambotcreator.creator.BotCreator
import com.example.telegrambotcreator.creator.helper.*
import com.example.telegrambotcreator.clean.presentation.fragments.creator_command.CreatorCommandFragment
import com.example.telegrambotcreator.clean.presentation.activity.viewmodel.TelegramViewModel
import com.github.kotlintelegrambot.entities.TelegramFile
import java.io.File

// region Answers

internal fun CreatorCommandFragment.stickerAnswer(
    binding: FragmentCreatorCommandBinding,
    viewModel: TelegramViewModel
) = with(binding) {
    when (this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer) {
        BotCreator.TypeAnswer.TEXT -> {
            if (isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if (isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if (isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if (isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if (isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if (isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if (isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if (isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if (isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if (it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if (isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addStickerListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}

internal fun CreatorCommandFragment.videoNoteAnswer(
    binding: FragmentCreatorCommandBinding,
    viewModel: TelegramViewModel
) = with(binding) {
    when (this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer) {
        BotCreator.TypeAnswer.TEXT -> {
            if (isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if (isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if (isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if (isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if (isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if (isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if (isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if (isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if (isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if (it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if (isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideoNotesListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}

internal fun CreatorCommandFragment.locationAnswer(
    binding: FragmentCreatorCommandBinding,
    viewModel: TelegramViewModel
) = with(binding) {
    when (this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer) {
        BotCreator.TypeAnswer.TEXT -> {
            if (isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if (isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if (isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if (isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if (isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if (isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if (isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if (isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if (isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if (it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if (isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addLocationsListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}

internal fun CreatorCommandFragment.contactAnswer(
    binding: FragmentCreatorCommandBinding,
    viewModel: TelegramViewModel
) = with(binding) {
    when (this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer) {
        BotCreator.TypeAnswer.TEXT -> {
            if (isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if (isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if (isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if (isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if (isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if (isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if (isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if (isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if (isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if (it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if (isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addContactsListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}

internal fun CreatorCommandFragment.voiceAnswer(
    binding: FragmentCreatorCommandBinding,
    viewModel: TelegramViewModel
) = with(binding) {
    when (this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer) {
        BotCreator.TypeAnswer.TEXT -> {
            if (isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if (isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if (isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if (isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if (isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if (isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if (isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if (isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if (isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if (it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if (isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addVoicesListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}

internal fun CreatorCommandFragment.videoAnswer(
    binding: FragmentCreatorCommandBinding,
    viewModel: TelegramViewModel
) = with(binding) {
    when (this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer) {
        BotCreator.TypeAnswer.TEXT -> {
            if (isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if (isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if (isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if (isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if (isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if (isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if (isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if (isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if (isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if (it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if (isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addVideosListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}

internal fun CreatorCommandFragment.photoAnswer(
    binding: FragmentCreatorCommandBinding,
    viewModel: TelegramViewModel
) = with(binding) {
    when (this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer) {
        BotCreator.TypeAnswer.TEXT -> {
            if (isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if (isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if (isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if (isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if (isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if (isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if (isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if (isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if (isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if (it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if (isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addPhotosListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}

internal fun CreatorCommandFragment.documentAnswer(
    binding: FragmentCreatorCommandBinding,
    viewModel: TelegramViewModel
) = with(binding) {
    when (this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer) {
        BotCreator.TypeAnswer.TEXT -> {
            if (isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if (isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if (isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if (isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if (isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if (isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if (isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if (isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if (isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if (it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if (isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addDocumentListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}

internal fun CreatorCommandFragment.animationAnswer(
    binding: FragmentCreatorCommandBinding,
    viewModel: TelegramViewModel
) = with(binding) {
    when (this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer) {
        BotCreator.TypeAnswer.TEXT -> {
            if (isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if (isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if (isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if (isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if (isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if (isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if (isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if (isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if (isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if (it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if (isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addAnimationListener(
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}

internal fun CreatorCommandFragment.commandAnswer(
    binding: FragmentCreatorCommandBinding,
    viewModel: TelegramViewModel
) = with(binding) {
    when (this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer) {
        BotCreator.TypeAnswer.TEXT -> {
            if (isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addCommandListener(
                command = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if (isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addCommandListener(
                command = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if (isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addCommandListener(
                command = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if (isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addCommandListener(
                command = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if (isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addCommandListener(
                command = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addCommandListener(
                command = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if (isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addCommandListener(
                command = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if (isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addCommandListener(
                command = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if (isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addCommandListener(
                command = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if (isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if (it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addCommandListener(
                command = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if (isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addCommandListener(
                command = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addCommandListener(
                command = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}

internal fun CreatorCommandFragment.textAnswer(
    binding: FragmentCreatorCommandBinding,
    viewModel: TelegramViewModel
) = with(binding) {
    when (this.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer) {
        BotCreator.TypeAnswer.TEXT -> {
            if (isNotGoodText(this, requireContext()))
                return
            viewModel?.chosenBot?.addTextListener(
                text = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.TEXT,
                answerText = this.inputAnswer.text.toString()
            )
        }
        BotCreator.TypeAnswer.ANIMATION -> {
            if (isNotGoodAnimation(this, requireContext()))
                return
            viewModel?.chosenBot?.addTextListener(
                text = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.ANIMATION,
                answerTGFile = TelegramFile.ByUrl(this.inputAnswer.text.toString())
            )
        }
        BotCreator.TypeAnswer.AUDIO -> {
            if (isNotGoodAudio(this, requireContext()))
                return
            viewModel?.chosenBot?.addTextListener(
                text = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.AUDIO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.DOCUMENT -> {
            if (isNotGoodDocument(this, requireContext()))
                return
            viewModel?.chosenBot?.addTextListener(
                text = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.DOCUMENT,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.PHOTO -> {
            if (isNotGoodPhoto(this, requireContext()))
                return
            viewModel?.chosenBot?.addTextListener(
                text = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.PHOTO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VIDEO -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addTextListener(
                text = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VIDEO,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.VOICE -> {
            if (isNotGoodVoice(this, requireContext()))
                return
            viewModel?.chosenBot?.addTextListener(
                text = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VOICE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
        BotCreator.TypeAnswer.CONTACT -> {
            if (isNotGoodContact(this, requireContext()))
                return
            viewModel?.chosenBot?.addTextListener(
                text = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.CONTACT,
                phoneNumber = this.inputPhone.text.toString(),
                firstName = this.inputFirstName.text.toString()
            )
        }
        BotCreator.TypeAnswer.LOCATION -> {
            if (isNotGoodLocation(this, requireContext()))
                return
            viewModel?.chosenBot?.addTextListener(
                text = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.LOCATION,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat()
            )
        }
        BotCreator.TypeAnswer.POLL -> {
            if (isNotGoodPoll(this, requireContext()))
                return
            val pollList = (this.inputAnswer.text.split(';') as ArrayList)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pollList.removeIf { it.isBlank() || it.isEmpty() }
            } else {
                val tmp = arrayListOf<String>()
                pollList.forEach { if (it.isNotEmpty() && it.isNotBlank()) tmp.add(it) }
                pollList.clear()
                pollList.addAll(tmp)
            }
            viewModel?.chosenBot?.addTextListener(
                text = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.POLL,
                question = this.inputQuestion.text.toString(),
                pollList = pollList
            )
        }
        BotCreator.TypeAnswer.VENUE -> {
            if (isNotGoodVenue(this, requireContext()))
                return
            viewModel?.chosenBot?.addTextListener(
                text = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VENUE,
                lat = this.inputLat.text.toString().toFloat(),
                lon = this.inputLon.text.toString().toFloat(),
                title = this.inputTitle.text.toString(),
                address = this.inputAddress.text.toString()
            )
        }
        BotCreator.TypeAnswer.VIDEO_NOTE -> {
            if (isNotGoodVideo(this, requireContext()))
                return
            viewModel?.chosenBot?.addTextListener(
                text = this.inputCommand.text.toString(),
                typeAnswer = BotCreator.TypeAnswer.VIDEO_NOTE,
                answerTGFile = TelegramFile.ByFile(File(uriSrc))
            )
        }
    }
    isSuccess = true
}
// endregion