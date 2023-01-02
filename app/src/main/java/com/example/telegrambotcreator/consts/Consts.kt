package com.example.telegrambotcreator.consts

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import com.example.telegrambotcreator.creator.model.listeners.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

// TODO: Вынести Типы и [isServiceRunning]
object Consts {
    const val TEST_API_TG_TOKEN = "5471488149:AAEnVX-doHCIoQDgrC8N5ITawrpi1k92s6E"
    const val REQUEST_CODE = 101
    const val CHANNEL_ID = 102

    val commandType: Type? = object : TypeToken<ArrayList<CommandTG?>?>() {}.type
    val animationsType: Type? = object : TypeToken<ArrayList<AnimationTG?>?>() {}.type
    val contactsType: Type? = object : TypeToken<ArrayList<ContactTG?>?>() {}.type
    val documentsType: Type? = object : TypeToken<ArrayList<DocumentTG?>?>() {}.type
    val locationsType: Type? = object : TypeToken<ArrayList<LocationTG?>?>() {}.type
    val photosType: Type? = object : TypeToken<ArrayList<PhotoTG?>?>() {}.type
    val stickersType: Type? = object : TypeToken<ArrayList<StickerTG?>?>() {}.type
    val textsType: Type? = object : TypeToken<ArrayList<TextTG?>?>() {}.type
    val videoNotesType: Type? = object : TypeToken<ArrayList<VideoNoteTG?>?>() {}.type
    val videosType: Type? = object : TypeToken<ArrayList<VideoTG?>?>() {}.type
    val voicesType: Type? = object : TypeToken<ArrayList<VoiceTG?>?>() {}.type

    fun isServiceRunning(serviceClass: Class<*>, activity: Activity): Boolean {
        val activityManager = activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        // Loop through the running services
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                // If the service is running then return true
                return true
            }
        }
        return false
    }

}