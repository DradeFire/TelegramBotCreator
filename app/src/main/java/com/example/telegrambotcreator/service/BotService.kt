package com.example.telegrambotcreator.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.telegrambotcreator.R
import com.example.telegrambotcreator.consts.Consts.CHANNEL_ID
import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.helper.restoreBot
import com.example.telegrambotcreator.model.datebase.model.BotDbModel
import com.example.telegrambotcreator.model.repository.Repository
import com.google.gson.Gson
import java.lang.Exception

class BotService: Service() {

    private var chosenBot: BotCreator? = null

    override fun onCreate() {
        super.onCreate()
        createNotification()
    }

    private fun createNotification() {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

             val serviceChannel = NotificationChannel(
                CHANNEL_ID.toString(), "My Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
             )

             val manager = getSystemService(
                 NotificationManager::class.java
             )

             manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        try {
            Toast.makeText(this, "Bot started", Toast.LENGTH_SHORT).show()
            val bot = intent!!.extras!!.getString("bot")
            chosenBot = Repository().getBotCreator().apply {
                restoreBot(Gson().fromJson(bot, BotDbModel::class.java) )
            }
            chosenBot?.build()?.builtBot?.startPolling()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notAndFor()
            }
        } catch (e: Exception){
            stopSelf()
        }

        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun notAndFor() {
        startForeground(
            CHANNEL_ID,
            Notification.Builder(this, CHANNEL_ID.toString())
                .setContentTitle(chosenBot?.nameOfBot)
                .setContentText("Bot is working")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()
        )
    }

    override fun onDestroy() {
        try {
            Toast.makeText(this, "Bot stopped", Toast.LENGTH_SHORT).show()
            chosenBot?.builtBot?.stopPolling()
            stopForeground(true)
        }
        catch (e: Exception) {

        }
        finally {
            super.onDestroy()
        }
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}