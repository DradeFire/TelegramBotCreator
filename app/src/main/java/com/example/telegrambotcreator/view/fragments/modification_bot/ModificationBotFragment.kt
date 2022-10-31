package com.example.telegrambotcreator.view.fragments.modification_bot

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.telegrambotcreator.databinding.FragmentModificationBotBinding
import com.example.telegrambotcreator.model.creator.helper.saveBot
import com.example.telegrambotcreator.view.base.BaseFragment

class ModificationBotFragment : BaseFragment<FragmentModificationBotBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentModificationBotBinding =
        FragmentModificationBotBinding::inflate

    override fun initObservers() {
        viewModel?.updateTrigger?.observe(viewLifecycleOwner) {
            it?.let {
                viewModel?.updateTrigger?.call()
                Toast.makeText(requireContext(), "Bot modified", Toast.LENGTH_SHORT).show()
                viewModel?.router?.exit()
            }
        }
    }

    override fun initButtons() {
        binding?.btCreateBot?.setOnClickListener {
            viewModel?.chosenBot?.nameOfBot = binding?.inputNameOfBot?.text.toString()
            viewModel?.chosenBot?.description = binding?.inputDescriptionOfCommand?.text.toString()
            viewModel?.chosenBot?.bot?.token = binding?.inputTokenOfBot?.text.toString()

            viewModel?.updateBot(viewModel?.chosenBot?.saveBot())
        }
    }

    override fun initStartValues() = with(binding!!) {
        inputNameOfBot.setText(viewModel?.chosenBot?.nameOfBot)
        inputDescriptionOfCommand.setText(viewModel?.chosenBot?.description)
        inputTokenOfBot.setText(viewModel?.chosenBot?.bot?.token)
    }

}