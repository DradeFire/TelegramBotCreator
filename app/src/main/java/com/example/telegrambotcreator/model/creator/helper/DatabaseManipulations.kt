package com.example.telegrambotcreator.model.creator.helper

import com.example.telegrambotcreator.consts.Consts.animationsType
import com.example.telegrambotcreator.consts.Consts.commandType
import com.example.telegrambotcreator.consts.Consts.contactsType
import com.example.telegrambotcreator.consts.Consts.documentsType
import com.example.telegrambotcreator.consts.Consts.locationsType
import com.example.telegrambotcreator.consts.Consts.photosType
import com.example.telegrambotcreator.consts.Consts.stickersType
import com.example.telegrambotcreator.consts.Consts.textsType
import com.example.telegrambotcreator.consts.Consts.videoNotesType
import com.example.telegrambotcreator.consts.Consts.videosType
import com.example.telegrambotcreator.consts.Consts.voicesType
import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.datebase.model.BotDbModel
import com.google.gson.Gson

// region Database Manipulations

fun BotCreator.saveBot(): BotDbModel = BotDbModel(
    if(botId == null) 0 else botId!!,
    nameOfBot,
    description,
    animations.size + commands.size + contacts.size + documents.size + locations.size + photos.size + stickers.size+ texts.size + videoNotes.size + videos.size + voices.size,
    bot.token,
    isPolling,
    Gson().toJson(animations),
    Gson().toJson(commands),
    Gson().toJson(contacts),
    Gson().toJson(documents),
    Gson().toJson(locations),
    Gson().toJson(photos),
    Gson().toJson(stickers),
    Gson().toJson(texts),
    Gson().toJson(videoNotes),
    Gson().toJson(videos),
    Gson().toJson(voices)
)
fun BotCreator.restoreBot(bot: BotDbModel) {

    botId = bot.id
    nameOfBot = bot.nameBot
    description = bot.description
    this.bot.token = bot.token
    isPolling = bot.isPolling

    commands.apply {
        clear()
        addAll(Gson().fromJson(bot.commands, commandType))
    }
    animations.apply {
        clear()
        addAll(Gson().fromJson(bot.animations, animationsType))
    }
    contacts.apply {
        clear()
        addAll(Gson().fromJson(bot.contacts, contactsType))
    }
    documents.apply {
        clear()
        addAll(Gson().fromJson(bot.documents, documentsType))
    }
    locations.apply {
        clear()
        addAll(Gson().fromJson(bot.locations, locationsType))
    }
    photos.apply {
        clear()
        addAll(Gson().fromJson(bot.photos, photosType))
    }
    stickers.apply {
        clear()
        addAll(Gson().fromJson(bot.stickers, stickersType))
    }
    texts.apply {
        clear()
        addAll(Gson().fromJson(bot.texts, textsType))
    }
    videoNotes.apply {
        clear()
        addAll(Gson().fromJson(bot.video_note, videoNotesType))
    }
    videos.apply {
        clear()
        addAll(Gson().fromJson(bot.videos, videosType))
    }
    voices.apply {
        clear()
        addAll(Gson().fromJson(bot.voices, voicesType))
    }
}
// endregion