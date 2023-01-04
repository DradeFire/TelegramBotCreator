package com.example.telegrambotcreator.creator.helper.listeners.ifelse

import com.example.telegrambotcreator.creator.BotCreator
import com.example.telegrambotcreator.creator.helper.answers.answerListener
import com.example.telegrambotcreator.creator.helper.listeners.base.*
import com.example.telegrambotcreator.creator.helper.listeners.base.addAnimation
import com.example.telegrambotcreator.creator.helper.listeners.base.addCommands
import com.example.telegrambotcreator.creator.helper.listeners.base.addVideos
import com.example.telegrambotcreator.creator.helper.listeners.base.addVoices
import com.example.telegrambotcreator.creator.model.listeners.*
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.entities.*
import kotlin.Exception

/**
 * #Тестируется#
 */
fun <E> BotCreator.ifElseListener(
    listenResult: ListenerTgBase,
    comparableValue: E,
    operation: CompareOperation<E>,
    bot: Bot,
    dispatcher: Dispatcher,
    message: Message
) = apply {
    val v1 = when (listenResult) {
        is TextTG -> {
            listenResult.text
        }
        else -> throw Exception()
    }

    operation.doWork(
            (if (comparableValue is Int) v1.toIntOrNull() else v1) as E,
            comparableValue,
            {
                answerListener(listenResult.apply { isTrue = true }, bot, dispatcher, message)
            },
            {
                answerListener(listenResult.apply { isTrue = false }, bot, dispatcher, message)
            }
    )

    listenResult.inListeners?.forEach { listener ->
        when (listener) {
            is AnimationTG -> addAnimation(dispatcher, listener)
            is CommandTG -> addCommands(dispatcher, listener)
            is ContactTG -> addContacts(dispatcher, listener)
            is DocumentTG -> addDocument(dispatcher, listener)
            is LocationTG -> addLocations(dispatcher, listener)
            is PhotoTG -> addPhotos(dispatcher, listener)
            is StickerTG -> addSticker(dispatcher, listener)
            is TextTG -> addText(dispatcher, listener)
            is VideoNoteTG -> addVideoNotes(dispatcher, listener)
            is VideoTG -> addVideos(dispatcher, listener)
            is VoiceTG -> addVoices(dispatcher, listener)
            else -> throw Exception()
        }
    }
}


sealed class CompareOperation<T> {
    abstract fun doWork(v1: T, v2: T, inIf: () -> Unit, inElse: () -> Unit)

    class Equal<T : Comparable<T>> : CompareOperation<T>() {
        override fun doWork(v1: T, v2: T, inIf: () -> Unit, inElse: () -> Unit) {
            if (v1 == v2) {
                inIf()
            } else {
                inElse()
            }
        }
    }

    class NotEqual<T : Comparable<T>> : CompareOperation<T>() {
        override fun doWork(v1: T, v2: T, inIf: () -> Unit, inElse: () -> Unit) {
            if (v1 != v2) {
                inIf()
            } else {
                inElse()
            }
        }
    }

    class More<T : Comparable<T>> : CompareOperation<T>() {
        override fun doWork(v1: T, v2: T, inIf: () -> Unit, inElse: () -> Unit) {
            if (v1 > v2) {
                inIf()
            } else {
                inElse()
            }
        }
    }

    class Less<T : Comparable<T>> : CompareOperation<T>() {
        override fun doWork(v1: T, v2: T, inIf: () -> Unit, inElse: () -> Unit) {
            if (v1 < v2) {
                inIf()
            } else {
                inElse()
            }
        }
    }

    class MoreOrEqual<T : Comparable<T>> : CompareOperation<T>() {
        override fun doWork(v1: T, v2: T, inIf: () -> Unit, inElse: () -> Unit) {
            if (v1 >= v2) {
                inIf()
            } else {
                inElse()
            }
        }
    }

    class LessOrEqual<T : Comparable<T>> : CompareOperation<T>() {
        override fun doWork(v1: T, v2: T, inIf: () -> Unit, inElse: () -> Unit) {
            if (v1 <= v2) {
                inIf()
            } else {
                inElse()
            }
        }
    }
}