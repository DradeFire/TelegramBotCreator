package com.example.telegrambotcreator.model.creator.helper

import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.model.ListenerTgBase

internal fun BotCreator.deleteCommand(id: Int, fatherId: Int? = null) {
    if (fatherId != null){
        val father = findFather(fatherId)
        father?.apply {
            var ind = -1
            inListeners?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inListeners as? ArrayList)?.removeAt(ind)
                return
            }
            inCallBack?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inCallBack as? ArrayList)?.removeAt(ind)
                return
            }
        }
    }
    else {
        var ind = -1
        commands.forEachIndexed { index, it ->
            val inD = removeCommand(it, id, index)
            if(inD != -1)
                ind = inD
        }
        if (ind != -1) {
            commands.removeAt(ind)
            return
        }
        texts.forEachIndexed { index, it ->
            val inD = removeCommand(it, id, index)
            if(inD != -1)
                ind = inD
        }
        if (ind != -1) {
            texts.removeAt(ind)
            return
        }
        animations.forEachIndexed { index, it ->
            val inD = removeCommand(it, id, index)
            if(inD != -1)
                ind = inD
        }
        if (ind != -1) {
            animations.removeAt(ind)
            return
        }
        documents.forEachIndexed { index, it ->
            val inD = removeCommand(it, id, index)
            if(inD != -1)
                ind = inD
        }
        if (ind != -1) {
            documents.removeAt(ind)
            return
        }
        stickers.forEachIndexed { index, it ->
            val inD = removeCommand(it, id, index)
            if(inD != -1)
                ind = inD
        }
        if (ind != -1) {
            stickers.removeAt(ind)
            return
        }
        voices.forEachIndexed { index, it ->
            val inD = removeCommand(it, id, index)
            if(inD != -1)
                ind = inD
        }
        if (ind != -1) {
            voices.removeAt(ind)
            return
        }
        videoNotes.forEachIndexed { index, it ->
            val inD = removeCommand(it, id, index)
            if(inD != -1)
                ind = inD
        }
        if (ind != -1) {
            videoNotes.removeAt(ind)
            return
        }
        videos.forEachIndexed { index, it ->
            val inD = removeCommand(it, id, index)
            if(inD != -1)
                ind = inD
        }
        if (ind != -1) {
            videos.removeAt(ind)
            return
        }
        photos.forEachIndexed { index, it ->
            val inD = removeCommand(it, id, index)
            if(inD != -1)
                ind = inD
        }
        if (ind != -1) {
            photos.removeAt(ind)
            return
        }
        locations.forEachIndexed { index, it ->
            val inD = removeCommand(it, id, index)
            if(inD != -1)
                ind = inD
        }
        if (ind != -1) {
            locations.removeAt(ind)
            return
        }
        contacts.forEachIndexed { index, it ->
            val inD = removeCommand(it, id, index)
            if(inD != -1)
                ind = inD
        }
        if (ind != -1) {
            contacts.removeAt(ind)
            return
        }
    }
}

private fun removeCommand(
    it: ListenerTgBase,
    id: Int,
    index: Int
): Int {
    if (it.id == id)
        return index
    return -1
}