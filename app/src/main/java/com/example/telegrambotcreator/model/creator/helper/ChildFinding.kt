package com.example.telegrambotcreator.model.creator.helper

import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.model.ListenerTgBase

// region Child Finding
internal fun BotCreator.findFather(id: Int): ListenerTgBase? {
    fun inCheck(it: ListenerTgBase): ListenerTgBase?{
        if (id == it.id){
            return it
        }

        val tmp = checkChildes(it, id)
        tmp?.let {
                tm-> return tm
        }
        return null
    }

    commands.forEach {
        val tmp = inCheck(it)
        tmp?.let { return tmp }
    }
    texts.forEach {
        val tmp = inCheck(it)
        tmp?.let { return tmp }
    }
    animations.forEach {
        val tmp = inCheck(it)
        tmp?.let { return tmp }
    }
    documents.forEach {
        val tmp = inCheck(it)
        tmp?.let { return tmp }
    }
    stickers.forEach {
        val tmp = inCheck(it)
        tmp?.let { return tmp }
    }
    voices.forEach {
        val tmp = inCheck(it)
        tmp?.let { return tmp }
    }
    videoNotes.forEach {
        val tmp = inCheck(it)
        tmp?.let { return tmp }
    }
    videos.forEach {
        val tmp = inCheck(it)
        tmp?.let { return tmp }
    }
    photos.forEach {
        val tmp = inCheck(it)
        tmp?.let { return tmp }
    }
    locations.forEach {
        val tmp = inCheck(it)
        tmp?.let { return tmp }
    }
    contacts.forEach {
        val tmp = inCheck(it)
        tmp?.let { return tmp }
    }
    return null
}
private fun findFatherRec(id: Int, list: List<ListenerTgBase>): ListenerTgBase? {
    list.forEach {
        if (it.id == id)
            return it
        val tmp = checkChildes(it, id)
        tmp?.let {
                tm-> return tm
        }
    }
    return null
}
private fun checkChildes(it: ListenerTgBase, id: Int): ListenerTgBase? {
    it.apply {
        inListeners?.let {
            val tmp = findFatherRec(id, it)
            tmp?.let { inIt -> return inIt }
        }
    }
    return null
}
// endregion