package com.example.telegrambotcreator.view.fragments.information_bot.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.telegrambotcreator.databinding.ItemListOfCommandsBinding
import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.helper.convertToCallbackType
import com.example.telegrambotcreator.model.creator.model.*
import com.example.telegrambotcreator.view.screens.Screens
import com.example.telegrambotcreator.viewmodel.TelegramViewModel

class CommandsAdapter(outCommands: List<BaseTgContainer>, private val viewModel: TelegramViewModel): RecyclerView.Adapter<CommandsAdapter.ViewHolder>() {

    private val commands: List<BaseTgContainer> = outCommands

    class ViewHolder(private val binding: ItemListOfCommandsBinding): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(baseTgContainer: BaseTgContainer, viewModel: TelegramViewModel) = with(binding){
            txNameOfCommand.text = when(baseTgContainer){
                is CommandTG -> {
                    "/${baseTgContainer.command}"
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
                    "\"${baseTgContainer.text}\""
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
                is VenueTG -> "Venue"
                is CallBackTG -> {
                    root.setBackgroundColor(Color.parseColor("#3949AB"))
                    when(baseTgContainer.typeCallback.convertToCallbackType()){
                        BotCreator.TypeCallback.INLINE -> "INLINE BUTTON"
                        BotCreator.TypeCallback.REPLY -> "REPLY BUTTON"
                    }
                }
            }
            txDescriptionOfCommand.text = "Type of answer: ${baseTgContainer.typeAnswer}"

            val countC = computeCount(baseTgContainer)
            txCountOfCommands.text = "Count of commands $countC"

            btOpenCommand.setOnClickListener {
                viewModel.commandsDeque.push(baseTgContainer)
                viewModel.choosenCommand++
                viewModel.router?.navigateTo(Screens.InformationCommandFrag())
            }

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
            countC += if(baseTgContainer.inAnimation.isNullOrEmpty()) 0 else baseTgContainer.inAnimation!!.size
            countC += if(baseTgContainer.inVideoNote.isNullOrEmpty()) 0 else baseTgContainer.inVideoNote!!.size
            countC += if(baseTgContainer.inVideo.isNullOrEmpty()) 0 else baseTgContainer.inVideo!!.size
            countC += if(baseTgContainer.inVoice.isNullOrEmpty()) 0 else baseTgContainer.inVoice!!.size
            countC += if(baseTgContainer.inCallBack.isNullOrEmpty()) 0 else baseTgContainer.inCallBack!!.size
            return countC
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemListOfCommandsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commands[position], viewModel)
    }

    override fun getItemCount(): Int = commands.size

}