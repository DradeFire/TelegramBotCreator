package com.example.telegrambotcreator.view.fragments.modification_bot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.telegrambotcreator.databinding.FragmentModificationBotBinding
import com.example.telegrambotcreator.model.creator.helper.saveBot
import com.example.telegrambotcreator.view.cicerone.App
import com.example.telegrambotcreator.viewmodel.TelegramViewModel

class ModificationBotFragment : Fragment() {

    private lateinit var binding: FragmentModificationBotBinding
    private lateinit var viewModel: TelegramViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentModificationBotBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[TelegramViewModel::class.java]
        viewModel.updateTrigger.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.updateTrigger.call()
                Toast.makeText(requireContext(), "Bot modified", Toast.LENGTH_SHORT).show()
                App.INSTANCE.router.exit()
            }
        }

        bindStartValues()
        bindButton()

        return binding.root
    }

    private fun bindButton() {
        binding.btCreateBot.setOnClickListener {
            viewModel.chosenBot.nameOfBot = binding.inputNameOfBot.text.toString()
            viewModel.chosenBot.description = binding.inputDescriptionOfCommand.text.toString()
            viewModel.chosenBot.bot.token = binding.inputTokenOfBot.text.toString()

            viewModel.updateBot(viewModel.chosenBot.saveBot())
        }
    }

    private fun bindStartValues() = with(binding){
        inputNameOfBot.setText(viewModel.chosenBot.nameOfBot)
        inputDescriptionOfCommand.setText(viewModel.chosenBot.description)
        inputTokenOfBot.setText(viewModel.chosenBot.bot.token)
    }

}