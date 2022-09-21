package com.example.telegrambotcreator.model.datebase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bots")
data class BotDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "nameBot")
    var nameBot: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "countOfCommands")
    var countOfCommands: Int,

    @ColumnInfo(name = "token")
    var token: String,

    var isPolling: Boolean,

    @ColumnInfo(name = "animations")
    var animations: String,

    @ColumnInfo(name = "commands")
    var commands: String,

    @ColumnInfo(name = "contacts")
    var contacts: String,

    @ColumnInfo(name = "documents")
    var documents: String,

    @ColumnInfo(name = "locations")
    var locations: String,

    @ColumnInfo(name = "photos")
    var photos: String,

    @ColumnInfo(name = "stickers")
    var stickers: String,

    @ColumnInfo(name = "texts")
    var texts: String,

    @ColumnInfo(name = "video_note")
    var video_note: String,

    @ColumnInfo(name = "videos")
    var videos: String,
    @ColumnInfo(name = "voices")
    var voices: String
)