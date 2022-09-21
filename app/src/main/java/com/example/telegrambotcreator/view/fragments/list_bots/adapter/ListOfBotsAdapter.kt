package com.example.telegrambotcreator.view.fragments.list_bots.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.telegrambotcreator.databinding.ItemListOfBotsBinding
import com.example.telegrambotcreator.model.creator.helper.restoreBot
import com.example.telegrambotcreator.model.datebase.model.BotDbModel
import com.example.telegrambotcreator.view.cicerone.App
import com.example.telegrambotcreator.view.cicerone.screens.Screens
import com.example.telegrambotcreator.viewmodel.TelegramViewModel

class ListOfBotsAdapter(outBots: List<BotDbModel>, private val viewModel: TelegramViewModel): RecyclerView.Adapter<ListOfBotsAdapter.ViewHolder>() {

    private val bots: List<BotDbModel> = outBots

    class ViewHolder(private val binding: ItemListOfBotsBinding): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(botDbModel: BotDbModel, viewModel: TelegramViewModel) = with(binding) {
            txNameOfBot.text = botDbModel.nameBot
            txCountOfCommands.text = "Count of commands:  ${botDbModel.countOfCommands}"
            btOpenBot.setOnClickListener {
                viewModel.chosenBot.restoreBot(botDbModel)
                App.INSTANCE.router.navigateTo(Screens.InformationBotFrag())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemListOfBotsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bots[position], viewModel)
    }

    override fun getItemCount(): Int = bots.size

}