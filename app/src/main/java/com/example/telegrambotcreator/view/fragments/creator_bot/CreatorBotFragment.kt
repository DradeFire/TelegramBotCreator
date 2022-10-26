package com.example.telegrambotcreator.view.fragments.creator_bot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.telegrambotcreator.consts.Consts.TEST_API_TG_TOKEN
import com.example.telegrambotcreator.databinding.FragmentCreatorBotBinding
import com.example.telegrambotcreator.model.creator.helper.saveBot
import com.example.telegrambotcreator.model.repository.Repository
import com.example.telegrambotcreator.viewmodel.TelegramViewModel

class CreatorBotFragment : Fragment() {

    private var binding: FragmentCreatorBotBinding? = null
    private var viewModel: TelegramViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatorBotBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStartVars()
        bindObservers()
        bindButtons()
    }

    private fun bindObservers() {
        viewModel?.addTrigger?.observe(viewLifecycleOwner){
            it?.let {
                viewModel?.addTrigger?.call()
                Toast.makeText(requireContext(), "Bot created", Toast.LENGTH_SHORT).show()
                viewModel?.router?.exit()
            }
        }
    }

    private fun bindButtons() {
        binding?.btCreateBot?.setOnClickListener {
            bindInput()
        }
    }

    private fun bindInput() {
        var nameOfBot: String
        var descriptionOfBot: String
        var token: String
        with(binding?.inputNameOfBot?.text.toString()){
            when{
                isNotEmpty() && isNotBlank() -> {
                    nameOfBot = this
                }
                else -> {
                    Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
                    return
                }
            }
        }
        with(binding?.inputDescriptionOfCommand?.text.toString()){
            when{
                isNotEmpty() && isNotBlank() -> {
                    descriptionOfBot = this
                }
                else -> {
                    Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
                    return
                }
            }
        }
        with(binding?.inputTokenOfBot?.text.toString()){
            token = when{
                isNotEmpty() && isNotBlank() -> {
                    this
                }
                else -> {
                    TEST_API_TG_TOKEN
                }
            }
        }

        when{
            nameOfBot.isNotEmpty()
                    && descriptionOfBot.isNotEmpty()
                    && token.isNotEmpty()
                    && nameOfBot.isNotBlank()
                    && descriptionOfBot.isNotBlank()
                    && token.isNotBlank()-> {
                viewModel?.chosenBot = Repository().getBotCreator()
                    .createBaseBot(token = token, name = nameOfBot, description = descriptionOfBot)
                viewModel?.addBot(viewModel?.chosenBot?.saveBot())
            }
            else -> {
                Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initStartVars() {
        viewModel = ViewModelProvider(requireActivity())[TelegramViewModel::class.java]
    }

    override fun onDestroyView() {
        binding = null
        viewModel = null
        super.onDestroyView()
    }

}