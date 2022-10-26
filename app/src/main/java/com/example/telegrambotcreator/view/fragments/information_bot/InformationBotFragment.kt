package com.example.telegrambotcreator.view.fragments.information_bot

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.telegrambotcreator.consts.Consts.animationsType
import com.example.telegrambotcreator.consts.Consts.commandType
import com.example.telegrambotcreator.consts.Consts.contactsType
import com.example.telegrambotcreator.consts.Consts.documentsType
import com.example.telegrambotcreator.consts.Consts.isServiceRunning
import com.example.telegrambotcreator.consts.Consts.locationsType
import com.example.telegrambotcreator.consts.Consts.photosType
import com.example.telegrambotcreator.consts.Consts.stickersType
import com.example.telegrambotcreator.consts.Consts.textsType
import com.example.telegrambotcreator.consts.Consts.videoNotesType
import com.example.telegrambotcreator.consts.Consts.videosType
import com.example.telegrambotcreator.consts.Consts.voicesType
import com.example.telegrambotcreator.databinding.FragmentInformationBotBinding
import com.example.telegrambotcreator.model.creator.helper.saveBot
import com.example.telegrambotcreator.model.creator.model.*
import com.example.telegrambotcreator.model.datebase.model.BotDbModel
import com.example.telegrambotcreator.service.BotService
import com.example.telegrambotcreator.view.screens.Screens
import com.example.telegrambotcreator.view.fragments.information_bot.adapter.CommandsAdapter
import com.example.telegrambotcreator.viewmodel.TelegramViewModel
import com.google.gson.Gson
import kotlinx.coroutines.*

class InformationBotFragment : Fragment() {

    private var binding: FragmentInformationBotBinding? = null
    private var viewModel: TelegramViewModel? = null
    private lateinit var curBot: BotDbModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentInformationBotBinding.inflate(inflater, container, false).also {
            binding = it
            return binding?.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindStartVars()
        bindView()
        bindButtons()
        bindRcView()
        bindSwitch()
    }

    private fun bindSwitch() {
            val isRun = isServiceRunning(BotService::class.java, requireActivity())
            if(!isRun)
                requireActivity().getSharedPreferences("shared", Context.MODE_PRIVATE).edit().putString("name_bot", "").apply()
        binding?.switch2?.isChecked = isRun
        binding?.switch2?.visibility = View.VISIBLE
        val serviceClass = BotService::class.java
        binding?.switch2?.setOnCheckedChangeListener { _, isEnabled ->
            when(isEnabled){
                true -> {
                    if(!isServiceRunning(serviceClass, requireActivity())){
                        requireActivity().getSharedPreferences("shared", Context.MODE_PRIVATE).edit()
                            .putString("name_bot", curBot.nameBot)
                            .apply()
                        startMyService(serviceClass)
                    }
                }
                false -> {
                    while(isServiceRunning(serviceClass, requireActivity())){
                        requireActivity().getSharedPreferences("shared", Context.MODE_PRIVATE).edit()
                            .putString("name_bot", "")
                            .apply()
                        stopMyService(serviceClass)
                    }
                }
            }
        }
    }

    private fun stopMyService(serviceClass: Class<BotService>) {
        val intent = Intent(requireContext(), serviceClass)
        requireContext().stopService(intent)
    }

    private fun startMyService(serviceClass: Class<BotService>) {
        val intent = Intent(requireContext(), serviceClass).apply {
            putExtra("bot", Gson().toJson(curBot))
        }
        requireContext().startService(intent)
    }

    private fun bindStartVars() {
        viewModel = ViewModelProvider(requireActivity())[TelegramViewModel::class.java]
        viewModel?.chosenBot?.also {
            curBot = it.saveBot()
        }
        viewModel?.deleteTrigger?.observe(viewLifecycleOwner){
            it?.let {
                viewModel?.deleteTrigger?.call()
                Toast.makeText(requireContext(), "Bot deleted", Toast.LENGTH_SHORT).show()
                viewModel?.router?.exit()
            }
        }
        val getBot = requireActivity().getSharedPreferences("shared", Context.MODE_PRIVATE).getString("name_bot", "")
        if(getBot != curBot.nameBot && getBot != "") {
            Toast.makeText(requireContext(), "Started another bot, stop it", Toast.LENGTH_SHORT).show()
            requireActivity().onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindView() = with(binding!!){
        txname.text = curBot.nameBot
        txdescription.text = curBot.description
        txcountCommands.text = "Count of commands: ${curBot.countOfCommands}"
    }

    private fun bindRcView() {
        CoroutineScope(Dispatchers.Default).launch {
            val commands = async { arrayListOf<BaseTgContainer>().apply {
                addAll(Gson().fromJson(curBot.commands, commandType))
                addAll(Gson().fromJson(curBot.animations, animationsType))
                addAll(Gson().fromJson(curBot.contacts, contactsType))
                addAll(Gson().fromJson(curBot.documents, documentsType))
                addAll(Gson().fromJson(curBot.locations, locationsType))
                addAll(Gson().fromJson(curBot.photos, photosType))
                addAll(Gson().fromJson(curBot.stickers, stickersType))
                addAll(Gson().fromJson(curBot.texts, textsType))
                addAll(Gson().fromJson(curBot.video_note, videoNotesType))
                addAll(Gson().fromJson(curBot.videos, videosType))
                addAll(Gson().fromJson(curBot.voices, voicesType))
            } }

            withContext(Dispatchers.Main){
                binding?.rcCommands?.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = CommandsAdapter(commands.await(), viewModel!!)
                    binding?.progressInformationBot?.visibility = View.GONE
                    visibility = View.VISIBLE
                }
            }

        }
    }

    private fun bindButtons() = with(binding!!){
        btAddCommand.setOnClickListener {
            viewModel?.router?.navigateTo(Screens.CreatorCommandFrag())
        }
        btModificationBot.setOnClickListener {
            viewModel?.router?.navigateTo(Screens.ModificationBotFrag())
        }
        btDeleteBot.setOnClickListener {
            if(isServiceRunning(BotService::class.java, requireActivity()))
                Toast.makeText(requireContext(), "Bot is running, stop it!", Toast.LENGTH_SHORT).show()
            else
                AlertDialog.Builder(requireContext())
                    .setTitle("Delete this bot?")
                    .setPositiveButton("Yes") { _, _ ->
                        viewModel?.deleteBot(curBot)
                    }
                    .setNegativeButton("No") { _, _ -> }
                    .show()
        }
    }

    override fun onDestroyView() {
        binding = null
        viewModel = null
        super.onDestroyView()
    }

}