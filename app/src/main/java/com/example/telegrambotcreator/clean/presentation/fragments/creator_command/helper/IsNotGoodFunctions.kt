package com.example.telegrambotcreator.clean.presentation.fragments.creator_command.helper

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.example.telegrambotcreator.databinding.FragmentCreatorCommandBinding

object IsNotGoodFunctions {
    fun isNotGoodVenue(binding: FragmentCreatorCommandBinding, context: Context): Boolean = with(binding!!) {
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
    fun isNotGoodPoll(binding: FragmentCreatorCommandBinding, context: Context): Boolean = with(binding!!) {
        val ans = this.inputQuestion.text.isNullOrEmpty()
                || this.inputQuestion.text.isNullOrBlank()
                || this.inputAnswer.text.isNullOrEmpty()
                || this.inputAnswer.text.isNullOrBlank()
        if(ans)
            Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodLocation(binding: FragmentCreatorCommandBinding, context: Context): Boolean = with(binding!!) {
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
    fun isNotGoodContact(binding: FragmentCreatorCommandBinding, context: Context): Boolean = with(binding!!) {
        val ans = this.inputFirstName.text.isNullOrEmpty()
                || this.inputFirstName.text.isNullOrBlank()
                || this.inputPhone.text.isNullOrEmpty()
                || !Patterns.PHONE.matcher(this.inputPhone.text).matches()
        if(ans)
            Toast.makeText(context, "Empty fields or there is no number", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodPhoto(binding: FragmentCreatorCommandBinding, context: Context): Boolean = with(binding!!) {
        val ans = this.btAddSrc.text.equals("Add image")
        if(ans)
            Toast.makeText(context, "No image added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodVoice(binding: FragmentCreatorCommandBinding, context: Context): Boolean = with(binding!!) {
        val ans = this.btAddSrc.text.equals("Add voice")
        if(ans)
            Toast.makeText(context, "No voice added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodDocument(binding: FragmentCreatorCommandBinding, context: Context): Boolean = with(binding!!) {
        val ans = this.btAddSrc.text.equals("Add document")
        if(ans)
            Toast.makeText(context, "No document added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodVideo(binding: FragmentCreatorCommandBinding, context: Context): Boolean = with(binding!!) {
        val ans = this.btAddSrc.text.equals("Add video")
        if(ans)
            Toast.makeText(context, "No video added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodAudio(binding: FragmentCreatorCommandBinding, context: Context): Boolean = with(binding!!) {
        val ans = this.btAddSrc.text.equals("Add audio")
        if(ans)
            Toast.makeText(context, "No audio added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodAnimation(binding: FragmentCreatorCommandBinding, context: Context): Boolean = with(binding!!) {
        val ans = this.inputAnswer.text.isNullOrEmpty()
                || this.inputAnswer.text.isNullOrBlank()
                || !Patterns.WEB_URL.matcher(this.inputAnswer.text).matches()
        if(ans)
            Toast.makeText(context, "Empty fields or there is no url", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodText(binding: FragmentCreatorCommandBinding, context: Context): Boolean = with(binding!!) {
        val ans = this.inputAnswer.text.isNullOrEmpty()
                || this.inputAnswer.text.isNullOrBlank()
        if(ans)
            Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
        return ans
    }
}