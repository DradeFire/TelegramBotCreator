package com.example.telegrambotcreator.creator

import com.example.telegrambotcreator.consts.Consts.TEST_API_TG_TOKEN
import com.example.telegrambotcreator.creator.helper.*
import com.example.telegrambotcreator.creator.model.listeners.*
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.*
import java.security.SecureRandom
import java.util.HashSet

class BotCreator {

    /**
     * Тип слушателя (то, что придёт от пользователя)
     */
    enum class TypeCommand {
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

    /**
     * Тип ответа (то, что отправиться пользователю)
     */
    enum class TypeAnswer {
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

    /**
     * Тип кнопки (прикрепленно к сообщению / под клавиатурой)
     */
    enum class TypeCallback {
        INLINE,
        REPLY
    }

    private val iDs = hashSetOf<Int>()
    fun getIDs() = iDs
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

    /**
     * Задаются базовые значения бота
     * @param token - Токен бота (получить у FatherBot в Телеграмме)
     * @param name - Название бота
     * @param description - Описание бота
     */
    fun createBaseBot(token: String = TEST_API_TG_TOKEN, name: String, description: String) = apply {
        bot.token = token
        nameOfBot = name
        this.description = description
    }

    internal fun createNewID(container: HashSet<Int> = iDs): Int {
        var id = SecureRandom().nextInt()
        while (container.contains(id)) id = SecureRandom().nextInt()

        container.add(id)
        return id
    }

    // region Build

    /**
     * Собирает бота
     */
    fun build() = apply {
        builtBot = bot.apply {
            dispatch {
                dispatchNow(this)
            }
        }.build()
    }

    private fun dispatchNow(dispatcher: Dispatcher) = with(dispatcher) {
        commands.forEach { addCommands(this, it) }
        texts.forEach { addText(this, it) }
        animations.forEach { addAnimation(this, it) }
        documents.forEach { addDocument(this, it) }
        stickers.forEach { addSticker(this, it) }
        voices.forEach { addVoices(this, it) }
        videoNotes.forEach { addVideoNotes(this, it) }
        videos.forEach { addVideos(this, it) }
        photos.forEach { addPhotos(this, it) }
        locations.forEach { addLocations(this, it) }
        contacts.forEach { addContacts(this, it) }
    }
    // endregion

}