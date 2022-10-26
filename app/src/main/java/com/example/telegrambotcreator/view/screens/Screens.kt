package com.example.telegrambotcreator.view.screens

import com.example.telegrambotcreator.view.fragments.check_video.CheckVideoFragment
import com.example.telegrambotcreator.view.fragments.creator_bot.CreatorBotFragment
import com.example.telegrambotcreator.view.fragments.creator_command.CreatorCommandFragment
import com.example.telegrambotcreator.view.fragments.information_bot.InformationBotFragment
import com.example.telegrambotcreator.view.fragments.information_command.InformationCommandFragment
import com.example.telegrambotcreator.view.fragments.list_bots.ListOfBotsFragment
import com.example.telegrambotcreator.view.fragments.modification_bot.ModificationBotFragment
import com.example.telegrambotcreator.view.fragments.modification_command.ModificationCommandFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun ListOfBotsFrag() = FragmentScreen { ListOfBotsFragment() }
    fun CreatorBotFrag() = FragmentScreen { CreatorBotFragment() }
    fun CreatorCommandFrag() = FragmentScreen { CreatorCommandFragment() }
    fun InformationCommandFrag() = FragmentScreen { InformationCommandFragment() }
    fun InformationBotFrag() = FragmentScreen { InformationBotFragment() }
    fun ModificationCommandFrag() = FragmentScreen { ModificationCommandFragment() }
    fun ModificationBotFrag() = FragmentScreen { ModificationBotFragment() }
    fun CheckVideoFrag() = FragmentScreen { CheckVideoFragment() }
}