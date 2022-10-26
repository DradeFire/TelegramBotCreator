package com.example.telegrambotcreator.model.creator

import com.example.telegrambotcreator.consts.Consts.TEST_API_TG_TOKEN
import com.example.telegrambotcreator.model.creator.helper.*
import com.example.telegrambotcreator.model.creator.model.*
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.*
import java.security.SecureRandom
import java.util.HashSet

class BotCreator {

    enum class TypeCommand{
        COMMAND,
        TEXT,
        ANIMATION,
        DOCUMENT,
        PHOTO,
        VIDEO,
        VOICE,
        CONTACT,
        LOCATION,
        VIDEO_NOTE,
        STICKER
    }
    enum class TypeAnswer{
        TEXT,
        ANIMATION,
        AUDIO,
        DOCUMENT,
        PHOTO,
        VIDEO,
        VOICE,
        CONTACT,
        LOCATION,
        POLL,
        VENUE,
        VIDEO_NOTE
    }
    enum class TypeCallback{
        INLINE,
        REPLY
    }

    private val IDs = hashSetOf<Int>()
    fun getIDs() = IDs
    internal val callbackIDs = hashSetOf<Int>()
    //fun getCallbackIDs() = callbackIDs

    internal val commands = arrayListOf<CommandTG>()
    internal val texts = arrayListOf<TextTG>()
    internal val animations = arrayListOf<AnimationTG>()
    internal val documents = arrayListOf<DocumentTG>()
    internal val stickers = arrayListOf<StickerTG>()
    internal val voices = arrayListOf<VoiceTG>()
    internal val videoNotes = arrayListOf<VideoNoteTG>()
    internal val videos = arrayListOf<VideoTG>()
    internal val photos = arrayListOf<PhotoTG>()
    internal val locations = arrayListOf<LocationTG>()
    internal val contacts = arrayListOf<ContactTG>()

    internal var botId: Int? = null
    internal lateinit var nameOfBot: String
    internal lateinit var description: String
    internal val bot = Bot.Builder()

    internal var isPolling = false
    lateinit var builtBot: Bot

    internal fun createBaseBot(token: String = TEST_API_TG_TOKEN, name: String, description: String) = apply {
        bot.token = token
        nameOfBot = name
        this.description = description
    }
    internal fun createNewID(container: HashSet<Int> = IDs): Int {
        var id = SecureRandom().nextInt()
        while (container.contains(id)) id = SecureRandom().nextInt()

        container.add(id)
        return id
    }

    // region Build

    fun build() = apply {
        builtBot = bot.apply {
            dispatch {
                dispatchNow(this)
            }
        }.build()
    }

    private fun dispatchNow(dispatcher: Dispatcher) = with(dispatcher) {
            addCommands(this, commands)
            addText(this, texts)
            addAnimation(this, animations)
            addDocument(this, documents)
            addSticker(this, stickers)
            addVoices(this, voices)
            addVideoNotes(this, videoNotes)
            addVideos(this, videos)
            addPhotos(this, photos)
            addLocations(this, locations)
            addContacts(this, contacts)
        }
    // endregion

}