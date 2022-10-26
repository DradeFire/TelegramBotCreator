package com.example.telegrambotcreator.model.creator.helper

import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.model.BaseTgContainer

// region Child Finding
internal fun BotCreator.findFather(id: Int): BaseTgContainer? {
    fun inCheck(it: BaseTgContainer): BaseTgContainer?{
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
        tmp?.let {
            return tmp
        }
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
    videos .forEach {
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
private fun findFatherRec(id: Int, list: List<BaseTgContainer>): BaseTgContainer? {
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
private fun checkChildes(it: BaseTgContainer, id: Int): BaseTgContainer? {
    it.apply {
        inAnimation?.let {
            val tmp = findFatherRec(id, it)
            tmp?.let { inIt -> return inIt }
        }
        inCommand?.let {
            val tmp = findFatherRec(id, it)
            tmp?.let { inIt -> return inIt }
        }
        inContact?.let {
            val tmp = findFatherRec(id, it)
            tmp?.let { inIt -> return inIt }
        }
        inDocument?.let {
            val tmp = findFatherRec(id, it)
            tmp?.let { inIt -> return inIt }
        }
        inLocation?.let {
            val tmp = findFatherRec(id, it)
            tmp?.let { inIt -> return inIt }
        }
        inPhoto?.let {
            val tmp = findFatherRec(id, it)
            tmp?.let { inIt -> return inIt }
        }
        inSticker?.let {
            val tmp = findFatherRec(id, it)
            tmp?.let { inIt -> return inIt }
        }
        inText?.let {
            val tmp = findFatherRec(id, it)
            tmp?.let { inIt -> return inIt }
        }
        inVideo?.let {
            val tmp = findFatherRec(id, it)
            tmp?.let { inIt -> return inIt }
        }
        inVoice?.let {
            val tmp = findFatherRec(id, it)
            tmp?.let { inIt -> return inIt }
        }
        inVideoNote?.let {
            val tmp = findFatherRec(id, it)
            tmp?.let { inIt -> return inIt }
        }
    }
    return null
}
// endregion