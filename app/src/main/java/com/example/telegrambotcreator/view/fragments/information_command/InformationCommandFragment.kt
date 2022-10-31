package com.example.telegrambotcreator.view.fragments.information_command

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.telegrambotcreator.consts.Consts.isServiceRunning
import com.example.telegrambotcreator.databinding.FragmentInformationBotBinding
import com.example.telegrambotcreator.databinding.FragmentInformationCommandBinding
import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.helper.*
import com.example.telegrambotcreator.model.creator.helper.convertToCallbackType
import com.example.telegrambotcreator.model.creator.helper.convertToType
import com.example.telegrambotcreator.model.creator.helper.findFather
import com.example.telegrambotcreator.model.creator.model.*
import com.example.telegrambotcreator.service.BotService
import com.example.telegrambotcreator.view.base.BaseFragment
import com.example.telegrambotcreator.view.screens.Screens
import com.example.telegrambotcreator.view.fragments.information_bot.adapter.CommandsAdapter
import com.example.telegrambotcreator.viewmodel.TelegramViewModel
import java.io.File

class InformationCommandFragment : BaseFragment<FragmentInformationCommandBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentInformationCommandBinding =
        FragmentInformationCommandBinding::inflate

    private lateinit var curCommand: ListenerTgBase
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false
    private var adapter: CommandsAdapter? = null

    override fun initStartValues() {
        viewModel = ViewModelProvider(requireActivity())[TelegramViewModel::class.java]
        curCommand = viewModel?.commandsDeque?.peek()!!
        viewModel?.updateTrigger?.observe(viewLifecycleOwner) {
            it?.let {
                viewModel?.updateTrigger?.call()

                Toast.makeText(requireContext(), "Command deleted", Toast.LENGTH_SHORT).show()
                viewModel?.router?.exit()
            }
        }
    }

    override fun initButtons() {
        binding?.btAddCommand?.setOnClickListener {
            viewModel?.isCreatingCallbackButton = false
            viewModel?.router?.navigateTo(Screens.CreatorCommandFrag())
        }
        binding?.btAddButton?.setOnClickListener {
            viewModel?.isCreatingCallbackButton = true
            viewModel?.router?.navigateTo(Screens.CreatorCommandFrag())
        }
        binding?.btModificationCommand?.setOnClickListener {
            when (curCommand) {
                is CallBackTG -> {
                    viewModel?.isCreatingCallbackButton = true
                    viewModel?.router?.navigateTo(Screens.ModificationCommandFrag())
                }
                else -> {
                    viewModel?.isCreatingCallbackButton = false
                    viewModel?.router?.navigateTo(Screens.ModificationCommandFrag())
                }
            }
        }
        binding?.btDeleteBot?.setOnClickListener {
            if (isServiceRunning(BotService::class.java, requireActivity()))
                Toast.makeText(requireContext(), "Bot is running, stop it!", Toast.LENGTH_SHORT)
                    .show()
            else
                AlertDialog.Builder(requireContext())
                    .setTitle("Delete this command?")
                    .setPositiveButton("Yes") { _, _ ->
                        viewModel?.apply {
                            choosenCommand--
                            commandsDeque.pop()
                            if (choosenCommand > 0) {
                                commandsDeque.pop()
                                commandsDeque.push(viewModel?.chosenBot?.findFather(curCommand.fatherId!!))
                            }
                        }
                        viewModel?.chosenBot?.deleteCommand(curCommand.id, curCommand.fatherId)
                        viewModel?.updateBot(viewModel?.chosenBot?.saveBot())
                    }
                    .setNegativeButton("No") { _, _ -> }
                    .show()
        }
    }

    override fun initRecyclerView() {
        val commands = arrayListOf<ListenerTgBase>().apply {
            curCommand.inListeners?.let { addAll(it) }
            curCommand.inCallBack?.let { addAll(it) }
        }

        if (adapter == null) {
            adapter = CommandsAdapter(commands, viewModel!!)
            binding?.rcCommands?.apply {
                layoutManager = LinearLayoutManager(requireContext())
                this.adapter = adapter
            }
            binding?.progressInformationCommand?.visibility = View.GONE
            binding?.rcCommands?.visibility = View.VISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initUI() = with(binding!!) {
        txTypeOfListener.text = "Type of command: " + when (curCommand) {
            is CommandTG -> {
                "COMMAND"
            }
            is AnimationTG -> {
                "ANIMATION"
            }
            is ContactTG -> {
                "CONTACT"
            }
            is DocumentTG -> {
                "DOCUMENT"
            }
            is LocationTG -> {
                "LOCATION"
            }
            is PhotoTG -> {
                "PHOTO"
            }
            is StickerTG -> {
                "STICKER"
            }
            is TextTG -> {
                "TEXT"
            }
            is VideoNoteTG -> {
                "VIDEO NOTE"
            }
            is VideoTG -> {
                "VIDEO"
            }
            is VoiceTG -> {
                "VOICE"
            }
            is CallBackTG -> {
                when ((curCommand as CallBackTG).typeCallback.convertToCallbackType()) {
                    BotCreator.TypeCallback.INLINE -> "INLINE BUTTON"
                    BotCreator.TypeCallback.REPLY -> "REPLY BUTTON"
                }
            }
            else -> ""
        }
        txListener.text = when (curCommand) {
            is CommandTG -> {
                "/${(curCommand as CommandTG).command}"
            }
            is AnimationTG -> {
                "Animation"
            }
            is ContactTG -> {
                "Contact"
            }
            is DocumentTG -> {
                "Document"
            }
            is LocationTG -> {
                "lat: ${curCommand.action?.lat}" +
                        "\n" +
                        "lon: ${curCommand.action?.lon}"
            }
            is PhotoTG -> {
                "Photo"
            }
            is StickerTG -> {
                "Sticker"
            }
            is TextTG -> {
                "\"${(curCommand as TextTG).text}\""
            }
            is VideoNoteTG -> {
                "VideoNote"
            }
            is VideoTG -> {
                "Video"
            }
            is VoiceTG -> {
                "Voice"
            }
            is CallBackTG -> {
                when ((curCommand as CallBackTG).typeCallback.convertToCallbackType()) {
                    BotCreator.TypeCallback.INLINE -> "INLINE BUTTON"
                    BotCreator.TypeCallback.REPLY -> "REPLY BUTTON"
                }
            }
            else -> ""
        }
        txTypeOfAnswer.text = when (curCommand.typeAnswer.convertToType()) {
            BotCreator.TypeAnswer.TEXT -> "Type answer: TEXT"
            BotCreator.TypeAnswer.PHOTO -> "Type answer: PHOTO"
            BotCreator.TypeAnswer.ANIMATION -> "Type of answer: ANIMATION"
            BotCreator.TypeAnswer.AUDIO -> "Type of answer: AUDIO"
            BotCreator.TypeAnswer.DOCUMENT -> "Type of answer: DOCUMENT"
            BotCreator.TypeAnswer.VIDEO -> "Type of answer: VIDEO"
            BotCreator.TypeAnswer.VOICE -> "Type of answer: VOICE"
            BotCreator.TypeAnswer.CONTACT -> "Type of answer: CONTACT"
            BotCreator.TypeAnswer.LOCATION -> "Type of answer: LOCATION"
            BotCreator.TypeAnswer.POLL -> "Type of answer: POLL"
            BotCreator.TypeAnswer.VENUE -> "Type of answer: VENUE"
            BotCreator.TypeAnswer.VIDEO_NOTE -> "Type of answer: VIDEO_NOTE"
        }
        when (curCommand.typeAnswer.convertToType()) {
            BotCreator.TypeAnswer.TEXT -> txAnswer.text = "\"${curCommand.action?.answerText}\""
            BotCreator.TypeAnswer.ANIMATION ->
                txAnswer.text = curCommand.action?.answerTGFile!!.substring(
                    curCommand.action?.answerTGFile!!.lastIndexOf('/') + 1
                )
            BotCreator.TypeAnswer.AUDIO -> {
                txAnswer.visibility = View.GONE
                btAudioPlay.visibility = View.VISIBLE
                mediaPlayer = MediaPlayer.create(
                    requireContext(),
                    File(curCommand.action?.answerTGFile).toUri()
                )
                btAudioPlay.setOnClickListener {
                    when (isPlaying) {
                        true -> {
                            isPlaying = false
                            btAudioPlay.text = "Play"
                            mediaPlayer?.pause()
                            mediaPlayer?.seekTo(0)
                        }
                        false -> {
                            isPlaying = true
                            btAudioPlay.text = "Stop"
                            mediaPlayer?.start()
                        }
                    }
                }
            }
            BotCreator.TypeAnswer.DOCUMENT ->
                txAnswer.text = curCommand.action?.answerTGFile!!.substring(
                    curCommand.action?.answerTGFile!!.lastIndexOf('/') + 1
                )
            BotCreator.TypeAnswer.PHOTO -> {
                txAnswer.visibility = View.GONE
                imageAnswer.visibility = View.VISIBLE
                val photo = File(curCommand.action?.answerTGFile)
                Glide.with(requireContext())
                    .load(photo)
                    .into(imageAnswer)
            }
            BotCreator.TypeAnswer.VIDEO -> {
                txAnswer.visibility = View.GONE
                btAudioPlay.visibility = View.VISIBLE
                btAudioPlay.setOnClickListener {
                    viewModel?.router?.navigateTo(Screens.CheckVideoFrag())
                }
            }
            BotCreator.TypeAnswer.VOICE -> {
                txAnswer.visibility = View.GONE
                btAudioPlay.visibility = View.VISIBLE
                mediaPlayer = MediaPlayer.create(
                    requireContext(),
                    File(curCommand.action?.answerTGFile).toUri()
                )
                btAudioPlay.setOnClickListener {
                    when (isPlaying) {
                        true -> {
                            isPlaying = false
                            btAudioPlay.text = "Play"
                            mediaPlayer?.pause()
                            mediaPlayer?.seekTo(0)
                        }
                        false -> {
                            isPlaying = true
                            btAudioPlay.text = "Stop"
                            mediaPlayer?.start()
                        }
                    }
                }
            }
            BotCreator.TypeAnswer.CONTACT -> {
                txAnswer.text = "First name: ${curCommand.action?.firstName}\n" +
                        "Phone number: ${curCommand.action?.phoneNumber}"
            }
            BotCreator.TypeAnswer.LOCATION -> {
                txAnswer.text = "Lat: ${curCommand.action?.lat}\n" +
                        "Lon: ${curCommand.action?.lon}"
            }
            BotCreator.TypeAnswer.POLL -> {
                var t = "Question: ${curCommand.action?.question}\n"
                curCommand.action?.pollList?.forEach { v -> t += "\n- $v" }
                txAnswer.text = t
            }
            BotCreator.TypeAnswer.VENUE -> {
                txAnswer.text = "Title: ${curCommand.action?.title}\n" +
                        "Address: ${curCommand.action?.address}\n" +
                        "Lat: ${curCommand.action?.lat}\n" +
                        "Lon: ${curCommand.action?.lon}"
            }
            BotCreator.TypeAnswer.VIDEO_NOTE -> {
                txAnswer.visibility = View.GONE
                btAudioPlay.visibility = View.VISIBLE
                btAudioPlay.setOnClickListener {
                    viewModel?.router?.navigateTo(Screens.CheckVideoFrag())
                }
            }
        }

        val countC = computeCount(curCommand)
        txCountCommands.text = "Count of commands $countC"
    }

    private fun computeCount(listenerTgBase: ListenerTgBase): Int {
        var countC = 0
        countC += if (listenerTgBase.inListeners.isNullOrEmpty()) 0 else listenerTgBase.inListeners!!.size
        countC += if (listenerTgBase.inCallBack.isNullOrEmpty()) 0 else listenerTgBase.inCallBack!!.size
        return countC
    }

    override fun onDetach() {
        mediaPlayer?.stop()
        mediaPlayer = null
        adapter = null
        super.onDetach()
    }

}