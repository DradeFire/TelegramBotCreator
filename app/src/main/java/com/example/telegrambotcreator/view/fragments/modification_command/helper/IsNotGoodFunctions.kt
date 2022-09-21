package com.example.telegrambotcreator.view.fragments.modification_command.helper

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.example.telegrambotcreator.databinding.FragmentModificationCommandBinding

object IsNotGoodFunctions {
    fun isNotGoodVenue(binding: FragmentModificationCommandBinding, context: Context): Boolean {
        val ans = binding.inputLat.text.isNullOrEmpty()
                || binding.inputLat.text.isNullOrBlank()
                || binding.inputLat.text.toString().toIntOrNull() == null
                || binding.inputLat.text.toString().toFloatOrNull() == null
                || binding.inputLon.text.isNullOrEmpty()
                || binding.inputLon.text.isNullOrBlank()
                || binding.inputLon.text.toString().toIntOrNull() == null
                || binding.inputLon.text.toString().toFloatOrNull() == null
                || binding.inputTitle.text.isNullOrEmpty()
                || binding.inputTitle.text.isNullOrBlank()
                || binding.inputAddress.text.isNullOrEmpty()
                || binding.inputAddress.text.isNullOrBlank()
        if(ans)
            Toast.makeText(context, "Empty fields or lat/lon aren't number", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodPoll(binding: FragmentModificationCommandBinding, context: Context): Boolean {
        val ans = binding.inputQuestion.text.isNullOrEmpty()
                || binding.inputQuestion.text.isNullOrBlank()
                || binding.inputAnswer.text.isNullOrEmpty()
                || binding.inputAnswer.text.isNullOrBlank()
        if(ans)
            Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodLocation(binding: FragmentModificationCommandBinding, context: Context): Boolean {
        val ans = binding.inputLat.text.isNullOrEmpty()
                || binding.inputLat.text.isNullOrBlank()
                || binding.inputLat.text.toString().toIntOrNull() == null
                || binding.inputLat.text.toString().toFloatOrNull() == null
                || binding.inputLon.text.isNullOrEmpty()
                || binding.inputLon.text.isNullOrBlank()
                || binding.inputLon.text.toString().toIntOrNull() == null
                || binding.inputLon.text.toString().toFloatOrNull() == null
        if(ans)
            Toast.makeText(context, "Empty fields or lat/lon aren't number", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodContact(binding: FragmentModificationCommandBinding, context: Context): Boolean {
        val ans = binding.inputFirstName.text.isNullOrEmpty()
                || binding.inputFirstName.text.isNullOrBlank()
                || binding.inputPhone.text.isNullOrEmpty()
                || !Patterns.PHONE.matcher(binding.inputPhone.text).matches()
        if(ans)
            Toast.makeText(context, "Empty fields or there is no number", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodPhoto(binding: FragmentModificationCommandBinding, context: Context): Boolean {
        val ans = binding.btAddSrc.text.equals("Add image")
        if(ans)
            Toast.makeText(context, "No image added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodVoice(binding: FragmentModificationCommandBinding, context: Context): Boolean {
        val ans = binding.btAddSrc.text.equals("Add voice")
        if(ans)
            Toast.makeText(context, "No voice added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodDocument(binding: FragmentModificationCommandBinding, context: Context): Boolean {
        val ans = binding.btAddSrc.text.equals("Add document")
        if(ans)
            Toast.makeText(context, "No document added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodVideo(binding: FragmentModificationCommandBinding, context: Context): Boolean {
        val ans = binding.btAddSrc.text.equals("Add video")
        if(ans)
            Toast.makeText(context, "No video added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodAudio(binding: FragmentModificationCommandBinding, context: Context): Boolean {
        val ans = binding.btAddSrc.text.equals("Add audio")
        if(ans)
            Toast.makeText(context, "No audio added", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodAnimation(binding: FragmentModificationCommandBinding, context: Context): Boolean {
        val ans = binding.inputAnswer.text.isNullOrEmpty()
                || binding.inputAnswer.text.isNullOrBlank()
                || !Patterns.WEB_URL.matcher(binding.inputAnswer.text).matches()
        if(ans)
            Toast.makeText(context, "Empty fields or there is no url", Toast.LENGTH_SHORT).show()
        return ans
    }
    fun isNotGoodText(binding: FragmentModificationCommandBinding, context: Context): Boolean {
        val ans = binding.inputAnswer.text.isNullOrEmpty()
                || binding.inputAnswer.text.isNullOrBlank()
        if(ans)
            Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
        return ans
    }
}