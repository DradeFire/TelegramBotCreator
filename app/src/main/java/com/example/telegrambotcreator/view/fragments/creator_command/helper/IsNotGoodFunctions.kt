package com.example.telegrambotcreator.view.fragments.creator_command.helper

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.example.telegrambotcreator.datathis.FragmentCreatorCommandthis

object IsNotGoodFunctions {
    fun isNotGoodVenue(this: FragmentCreatorCommandthis, context: Context): Boolean {
        val ans = this.inputLat.text.isNullOrEmpty()
                || this.inputLat.text.isNullOrBlank()
                || this.inputLat.text.toString().toIntOrNull() == null
                || this.inputLat.text.toString().toFloatOrNull() == null
                || this.inputLon.text.isNullOrEmpty()
                || this.inputLon.text.isNullOrBlank()
                || this.inputLon.text.toString().toIntOrNull() == null
                || this.inputLon.text.toString().toFloatOrNull() == null
                || this.inputTitle.text.isNullOrEmpty()
                || this.inputTitle.text.isNullOrBlank()
                || this.inputAddress.text.isNullOrEmpty()
                || this.inputAddress.text.isNullOrBlank()
        if(ans)
            Toast.makeText(context, "Empty fields or lat/lon aren't number", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodPoll(this: FragmentCreatorCommandthis, context: Context): Boolean {
        val ans = this.inputQuestion.text.isNullOrEmpty()
                || this.inputQuestion.text.isNullOrBlank()
                || this.inputAnswer.text.isNullOrEmpty()
                || this.inputAnswer.text.isNullOrBlank()
        if(ans)
            Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodLocation(this: FragmentCreatorCommandthis, context: Context): Boolean {
        val ans = this.inputLat.text.isNullOrEmpty()
                || this.inputLat.text.isNullOrBlank()
                || this.inputLat.text.toString().toIntOrNull() == null
                || this.inputLat.text.toString().toFloatOrNull() == null
                || this.inputLon.text.isNullOrEmpty()
                || this.inputLon.text.isNullOrBlank()
                || this.inputLon.text.toString().toIntOrNull() == null
                || this.inputLon.text.toString().toFloatOrNull() == null
        if(ans)
            Toast.makeText(context, "Empty fields or lat/lon aren't number", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodContact(this: FragmentCreatorCommandthis, context: Context): Boolean {
        val ans = this.inputFirstName.text.isNullOrEmpty()
                || this.inputFirstName.text.isNullOrBlank()
                || this.inputPhone.text.isNullOrEmpty()
                || !Patterns.PHONE.matcher(this.inputPhone.text).matches()
        if(ans)
            Toast.makeText(context, "Empty fields or there is no number", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodPhoto(this: FragmentCreatorCommandthis, context: Context): Boolean {
        val ans = this.btAddSrc.text.equals("Add image")
        if(ans)
            Toast.makeText(context, "No image added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodVoice(this: FragmentCreatorCommandthis, context: Context): Boolean {
        val ans = this.btAddSrc.text.equals("Add voice")
        if(ans)
            Toast.makeText(context, "No voice added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodDocument(this: FragmentCreatorCommandthis, context: Context): Boolean {
        val ans = this.btAddSrc.text.equals("Add document")
        if(ans)
            Toast.makeText(context, "No document added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodVideo(this: FragmentCreatorCommandthis, context: Context): Boolean {
        val ans = this.btAddSrc.text.equals("Add video")
        if(ans)
            Toast.makeText(context, "No video added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodAudio(this: FragmentCreatorCommandthis, context: Context): Boolean {
        val ans = this.btAddSrc.text.equals("Add audio")
        if(ans)
            Toast.makeText(context, "No audio added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodAnimation(this: FragmentCreatorCommandthis, context: Context): Boolean {
        val ans = this.inputAnswer.text.isNullOrEmpty()
                || this.inputAnswer.text.isNullOrBlank()
                || !Patterns.WEB_URL.matcher(this.inputAnswer.text).matches()
        if(ans)
            Toast.makeText(context, "Empty fields or there is no url", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodText(this: FragmentCreatorCommandthis, context: Context): Boolean {
        val ans = this.inputAnswer.text.isNullOrEmpty()
                || this.inputAnswer.text.isNullOrBlank()
        if(ans)
            Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
        return ans
    }
}