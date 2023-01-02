package com.example.telegrambotcreator.clean.presentation.fragments.information_bot.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.telegrambotcreator.databinding.ItemListOfCommandsBinding
import com.example.telegrambotcreator.creator.BotCreator
import com.example.telegrambotcreator.creator.helper.convertToCallbackType
import com.example.telegrambotcreator.clean.presentation.screens.Screens
import com.example.telegrambotcreator.clean.presentation.activity.viewmodel.TelegramViewModel
import com.example.telegrambotcreator.creator.model.listeners.*

class CommandsAdapter(outCommands: List<ListenerTgBase>, private val viewModel: TelegramViewModel) :
    RecyclerView.Adapter<CommandsAdapter.ViewHolder>() {

    private val commands: List<ListenerTgBase> = outCommands

    class ViewHolder(private val binding: ItemListOfCommandsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(listenerTgBase: ListenerTgBase, viewModel: TelegramViewModel) = with(binding) {
            txNameOfCommand.text = when (listenerTgBase) {
                is CommandTG -> {
                    "/${listenerTgBase.command}"
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
                    "Location"
                }
                is PhotoTG -> {
                    "Photo"
                }
                is StickerTG -> {
                    "Sticker"
                }
                is TextTG -> {
                    "\"${listenerTgBase.text}\""
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
                    root.setBackgroundColor(Color.parseColor("#3949AB"))
                    when (listenerTgBase.typeCallback.convertToCallbackType()) {
                        BotCreator.TypeCallback.INLINE -> "INLINE BUTTON"
                        BotCreator.TypeCallback.REPLY -> "REPLY BUTTON"
                    }
                }
                else -> ""
            }
            txDescriptionOfCommand.text = "Type of answer: ${listenerTgBase.typeAnswer}"

            val countC = computeCount(listenerTgBase)
            txCountOfCommands.text = "Count of commands $countC"

            btOpenCommand.setOnClickListener {
                viewModel.commandsDeque.push(listenerTgBase)
                viewModel.choosenCommand++
                viewModel.router?.navigateTo(Screens.InformationCommandFrag())
            }

        }

        private fun computeCount(listenerTgBase: ListenerTgBase): Int {
            var countC = 0
            countC += if (listenerTgBase.inListeners.isNullOrEmpty()) 0 else listenerTgBase.inListeners!!.size
            countC += if (listenerTgBase.inCallBack.isNullOrEmpty()) 0 else listenerTgBase.inCallBack!!.size
            return countC
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemListOfCommandsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commands[position], viewModel)
    }

    override fun getItemCount(): Int = commands.size

}