package com.example.telegrambotcreator.clean.presentation.fragments.list_bots.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.telegrambotcreator.databinding.ItemListOfBotsBinding
import com.example.telegrambotcreator.creator.helper.restoreBot
import com.example.telegrambotcreator.creator.model.database.BotDatabaseModel
import com.example.telegrambotcreator.clean.presentation.screens.Screens
import com.example.telegrambotcreator.clean.presentation.activity.viewmodel.TelegramViewModel

class ListOfBotsAdapter(
    outBots: List<BotDatabaseModel>,
    private val viewModel: TelegramViewModel
) : RecyclerView.Adapter<ListOfBotsAdapter.ViewHolder>() {

    private val bots: List<BotDatabaseModel> = outBots

    class ViewHolder(private val binding: ItemListOfBotsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(botDatabaseModel: BotDatabaseModel, viewModel: TelegramViewModel) = with(binding) {
            txNameOfBot.text = botDatabaseModel.nameBot
            txCountOfCommands.text = "Count of commands: ${botDatabaseModel.countOfCommands}"
            btOpenBot.setOnClickListener {
                viewModel.chosenBot.restoreBot(botDatabaseModel)
                viewModel.router?.navigateTo(Screens.InformationBotFrag())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemListOfBotsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bots[position], viewModel)
    }

    override fun getItemCount(): Int = bots.size

}