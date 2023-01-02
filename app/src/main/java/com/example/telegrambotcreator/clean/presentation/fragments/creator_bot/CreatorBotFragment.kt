package com.example.telegrambotcreator.clean.presentation.fragments.creator_bot

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.telegrambotcreator.consts.Consts.TEST_API_TG_TOKEN
import com.example.telegrambotcreator.databinding.FragmentCreatorBotBinding
import com.example.telegrambotcreator.creator.helper.saveBot
import com.example.telegrambotcreator.repository.Repository
import com.example.telegrambotcreator.clean.presentation.base.BaseFragment

class CreatorBotFragment : BaseFragment<FragmentCreatorBotBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCreatorBotBinding =
        FragmentCreatorBotBinding::inflate

    override fun initObservers() {
        viewModel?.addTrigger?.observe(viewLifecycleOwner) {
            it?.let {
                viewModel?.addTrigger?.call()
                Toast.makeText(requireContext(), "Bot created", Toast.LENGTH_SHORT).show()
                viewModel?.router?.exit()
            }
        }
    }

    override fun initButtons() {
        binding?.btCreateBot?.setOnClickListener {
            bindInput()
        }
    }

    private fun bindInput() {
        var nameOfBot: String
        var descriptionOfBot: String
        var token: String
        with(binding?.inputNameOfBot?.text.toString()) {
            when {
                isNotEmpty() && isNotBlank() -> {
                    nameOfBot = this
                }
                else -> {
                    Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
                    return
                }
            }
        }
        with(binding?.inputDescriptionOfCommand?.text.toString()) {
            when {
                isNotEmpty() && isNotBlank() -> {
                    descriptionOfBot = this
                }
                else -> {
                    Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
                    return
                }
            }
        }
        with(binding?.inputTokenOfBot?.text.toString()) {
            token = when {
                isNotEmpty() && isNotBlank() -> {
                    this
                }
                else -> {
                    TEST_API_TG_TOKEN
                }
            }
        }

        when {
            nameOfBot.isNotEmpty()
                    && descriptionOfBot.isNotEmpty()
                    && token.isNotEmpty()
                    && nameOfBot.isNotBlank()
                    && descriptionOfBot.isNotBlank()
                    && token.isNotBlank() -> {
                viewModel?.chosenBot = Repository().getBotCreator()
                    .createBaseBot(token = token, name = nameOfBot, description = descriptionOfBot)
                viewModel?.addBot(viewModel?.chosenBot?.saveBot())
            }
            else -> {
                Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

}