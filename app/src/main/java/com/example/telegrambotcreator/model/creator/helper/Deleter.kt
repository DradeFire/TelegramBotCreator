package com.example.telegrambotcreator.model.creator.helper

import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.model.BaseTgContainer

internal fun BotCreator.deleteCommand(id: Int, fatherId: Int? = null) {
    if (fatherId != null){
        val father = findFather(fatherId)
        father?.apply {
            var ind = -1
            inCommand?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inCommand as ArrayList).removeAt(ind)
                return
            }
            inText?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inText as ArrayList).removeAt(ind)
                return
            }
            inAnimation?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inAnimation as ArrayList).removeAt(ind)
                return
            }
            inDocument?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inDocument as ArrayList).removeAt(ind)
                return
            }
            inSticker?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inSticker as ArrayList).removeAt(ind)
                return
            }
            inVoice?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inVoice as ArrayList).removeAt(ind)
                return
            }
            inVideoNote?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inVideoNote as ArrayList).removeAt(ind)
                return
            }
            inVideo?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inVideo as ArrayList).removeAt(ind)
                return
            }
            inPhoto?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inPhoto as ArrayList).removeAt(ind)
                return
            }
            inLocation?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inLocation as ArrayList).removeAt(ind)
                return
            }
            inContact?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inContact as ArrayList).removeAt(ind)
                return
            }
            inCallBack?.forEachIndexed { index, it ->
                val inD = removeCommand(it, id, index)
                if(inD != -1)
                    ind = inD
            }
            if (ind != -1) {
                (inCallBack as ArrayList).removeAt(ind)
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
    it: BaseTgContainer,
    id: Int,
    index: Int
): Int {
    if (it.id == id)
        return index
    return -1
}