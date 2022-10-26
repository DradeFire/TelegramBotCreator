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
import com.example.telegrambotcreator.databinding.FragmentInformationCommandBinding
import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.helper.*
import com.example.telegrambotcreator.model.creator.helper.convertToCallbackType
import com.example.telegrambotcreator.model.creator.helper.convertToType
import com.example.telegrambotcreator.model.creator.helper.findFather
import com.example.telegrambotcreator.model.creator.model.*
import com.example.telegrambotcreator.service.BotService
import com.example.telegrambotcreator.view.screens.Screens
import com.example.telegrambotcreator.view.fragments.information_bot.adapter.CommandsAdapter
import com.example.telegrambotcreator.viewmodel.TelegramViewModel
import java.io.File

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class InformationCommandFragment : Fragment() {
    private var binding: FragmentInformationCommandBinding? = null
    private var viewModel: TelegramViewModel? = null
    private lateinit var curCommand: BaseTgContainer
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FragmentInformationCommandBinding.inflate(inflater, container, false).also {
            binding = it
            return binding?.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initStartVars()
        bindViews()
        bindButtons()
        bindRcView()
    }

    private fun bindButtons() {
        binding?.btAddCommand?.setOnClickListener {
            viewModel?.isCreatingCallbackButton = false
            viewModel?.router?.navigateTo(Screens.CreatorCommandFrag())
        }
        binding?.btAddButton?.setOnClickListener {
            viewModel?.isCreatingCallbackButton = true
            viewModel?.router?.navigateTo(Screens.CreatorCommandFrag())
        }
        binding?.btModificationCommand?.setOnClickListener {
            when(curCommand){
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
            if(isServiceRunning(BotService::class.java, requireActivity()))
                Toast.makeText(requireContext(), "Bot is running, stop it!", Toast.LENGTH_SHORT).show()
            else
                AlertDialog.Builder(requireContext())
                    .setTitle("Delete this command?")
                    .setPositiveButton("Yes") { _, _ ->
                        viewModel?.apply {
                            choosenCommand--
                            commandsDeque.pop()
                            if (choosenCommand > 0){
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

    private fun bindRcView() {
        val commands = arrayListOf<BaseTgContainer>().apply {
            curCommand.inCommand?.let { addAll(it) }
            curCommand.inAnimation?.let { addAll(it) }
            curCommand.inContact?.let { addAll(it) }
            curCommand.inDocument?.let { addAll(it) }
            curCommand.inLocation?.let { addAll(it) }
            curCommand.inPhoto?.let { addAll(it) }
            curCommand.inSticker?.let { addAll(it) }
            curCommand.inText?.let { addAll(it) }
            curCommand.inVideoNote?.let { addAll(it) }
            curCommand.inVideo?.let { addAll(it) }
            curCommand.inVoice?.let { addAll(it) }
            curCommand.inCallBack?.let { addAll(it) }
        }

        binding?.rcCommands?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CommandsAdapter(commands, viewModel!!)
            binding?.progressInformationCommand?.visibility = View.GONE
            binding?.rcCommands?.visibility = View.VISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindViews() = with(binding!!){
        txTypeOfListener.text = "Type of command: " + when(curCommand){
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
            is VenueTG -> "VENUE"
            is CallBackTG -> {
                when((curCommand as CallBackTG).typeCallback.convertToCallbackType()){
                    BotCreator.TypeCallback.INLINE -> "INLINE BUTTON"
                    BotCreator.TypeCallback.REPLY -> "REPLY BUTTON"
                }
            }
        }
        txListener.text = when(curCommand){
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
                "lat: ${curCommand.lat}" +
                        "\n" +
                        "lon: ${curCommand.lon}"
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
                when((curCommand as CallBackTG).typeCallback.convertToCallbackType()){
                    BotCreator.TypeCallback.INLINE -> "INLINE BUTTON"
                    BotCreator.TypeCallback.REPLY -> "REPLY BUTTON"
                }
            }
            is VenueTG -> "Venue"
        }
        txTypeOfAnswer.text = when(curCommand.typeAnswer.convertToType()){
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
        when(curCommand.typeAnswer.convertToType()){
            BotCreator.TypeAnswer.TEXT -> txAnswer.text = "\"${curCommand.answerText}\""
            BotCreator.TypeAnswer.ANIMATION ->
                txAnswer.text = curCommand.answerTGFile!!.substring(curCommand.answerTGFile!!.lastIndexOf('/') + 1)
            BotCreator.TypeAnswer.AUDIO -> {
                txAnswer.visibility = View.GONE
                btAudioPlay.visibility = View.VISIBLE
                mediaPlayer = MediaPlayer.create(requireContext(), File(curCommand.answerTGFile).toUri())
                btAudioPlay.setOnClickListener {
                    when(isPlaying){
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
                txAnswer.text = curCommand.answerTGFile!!.substring(curCommand.answerTGFile!!.lastIndexOf('/') + 1)
            BotCreator.TypeAnswer.PHOTO -> {
                txAnswer.visibility = View.GONE
                imageAnswer.visibility = View.VISIBLE
                val photo = File(curCommand.answerTGFile)
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
                mediaPlayer = MediaPlayer.create(requireContext(), File(curCommand.answerTGFile).toUri())
                btAudioPlay.setOnClickListener {
                    when(isPlaying){
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
                txAnswer.text = "First name: ${curCommand.firstName}\n" +
                        "Phone number: ${curCommand.phoneNumber}"
            }
            BotCreator.TypeAnswer.LOCATION -> {
                txAnswer.text = "Lat: ${curCommand.lat}\n" +
                        "Lon: ${curCommand.lon}"
            }
            BotCreator.TypeAnswer.POLL -> {
                var t = "Question: ${curCommand.question}\n"
                curCommand.pollList?.forEach { v -> t += "\n- $v" }
                txAnswer.text =  t
            }
            BotCreator.TypeAnswer.VENUE -> {
                txAnswer.text = "Title: ${curCommand.title}\n" +
                        "Address: ${curCommand.address}\n" +
                        "Lat: ${curCommand.lat}\n" +
                        "Lon: ${curCommand.lon}"
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

    private fun computeCount(baseTgContainer: BaseTgContainer): Int {
        var countC = 0
        countC += if(baseTgContainer.inAnimation.isNullOrEmpty()) 0 else baseTgContainer.inAnimation!!.size
        countC += if(baseTgContainer.inCommand.isNullOrEmpty()) 0 else baseTgContainer.inCommand!!.size
        countC += if(baseTgContainer.inContact.isNullOrEmpty()) 0 else baseTgContainer.inContact!!.size
        countC += if(baseTgContainer.inDocument.isNullOrEmpty()) 0 else baseTgContainer.inDocument!!.size
        countC += if(baseTgContainer.inLocation.isNullOrEmpty()) 0 else baseTgContainer.inLocation!!.size
        countC += if(baseTgContainer.inPhoto.isNullOrEmpty()) 0 else baseTgContainer.inPhoto!!.size
        countC += if(baseTgContainer.inSticker.isNullOrEmpty()) 0 else baseTgContainer.inSticker!!.size
        countC += if(baseTgContainer.inText.isNullOrEmpty()) 0 else baseTgContainer.inText!!.size
        countC += if(baseTgContainer.inVideoNote.isNullOrEmpty()) 0 else baseTgContainer.inVideoNote!!.size
        countC += if(baseTgContainer.inVideo.isNullOrEmpty()) 0 else baseTgContainer.inVideo!!.size
        countC += if(baseTgContainer.inVoice.isNullOrEmpty()) 0 else baseTgContainer.inVoice!!.size
        countC += if(baseTgContainer.inCallBack.isNullOrEmpty()) 0 else baseTgContainer.inCallBack!!.size
        return countC
    }

    private fun initStartVars() {
        viewModel = ViewModelProvider(requireActivity())[TelegramViewModel::class.java]
        curCommand = viewModel?.commandsDeque?.peek()!!
        viewModel?.updateTrigger?.observe(viewLifecycleOwner){
            it?.let {
                viewModel?.updateTrigger?.call()
                Toast.makeText(requireContext(), "Command deleted", Toast.LENGTH_SHORT).show()
                viewModel?.router?.exit()
            }
        }
    }

    override fun onDestroyView() {
        mediaPlayer?.stop()
        mediaPlayer = null
        binding = null
        viewModel = null
        super.onDestroyView()
    }

}