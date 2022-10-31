package com.example.telegrambotcreator.view.fragments.creator_command

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.telegrambotcreator.consts.Consts.REQUEST_CODE
import com.example.telegrambotcreator.databinding.FragmentCreatorCommandBinding
import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.helper.findFather
import com.example.telegrambotcreator.model.creator.helper.saveBot
import com.example.telegrambotcreator.view.base.BaseFragment
import com.example.telegrambotcreator.view.fragments.creator_command.helper.*
import com.hbisoft.pickit.PickiT
import com.hbisoft.pickit.PickiTCallbacks
import java.lang.Exception

class CreatorCommandFragment : BaseFragment<FragmentCreatorCommandBinding>(), PickiTCallbacks {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCreatorCommandBinding =
        FragmentCreatorCommandBinding::inflate

    private lateinit var pickiT: PickiT
    internal var isSuccess = false
    internal lateinit var uriSrc: String
    internal val btClickListenerCreatorCommandFragment = View.OnClickListener {
        val cur = if (viewModel?.commandsDeque.isNullOrEmpty())
            null
        else
            viewModel?.commandsDeque?.peek()

        when (cur) {
            null -> // Bot
                when (binding?.spinnerTypeOfCommand?.selectedItem as BotCreator.TypeCommand) {
                    BotCreator.TypeCommand.COMMAND -> {
                        if (binding?.inputCommand?.text.isNullOrEmpty() || binding?.inputCommand?.text.toString()
                                .contains(' ')
                        )
                            return@OnClickListener
                        commandAnswer(binding!!, viewModel!!)
                    }
                    BotCreator.TypeCommand.TEXT -> {
                        if (binding?.inputCommand?.text.isNullOrEmpty())
                            return@OnClickListener
                        textAnswer(binding!!, viewModel!!)
                    }
                    BotCreator.TypeCommand.ANIMATION -> animationAnswer(binding!!, viewModel!!)
                    BotCreator.TypeCommand.DOCUMENT -> documentAnswer(binding!!, viewModel!!)
                    BotCreator.TypeCommand.PHOTO -> photoAnswer(binding!!, viewModel!!)
                    BotCreator.TypeCommand.VIDEO -> videoAnswer(binding!!, viewModel!!)
                    BotCreator.TypeCommand.VOICE -> voiceAnswer(binding!!, viewModel!!)
                    BotCreator.TypeCommand.CONTACT -> contactAnswer(binding!!, viewModel!!)
                    BotCreator.TypeCommand.LOCATION -> locationAnswer(binding!!, viewModel!!)
                    BotCreator.TypeCommand.VIDEO_NOTE -> videoNoteAnswer(binding!!, viewModel!!)
                    BotCreator.TypeCommand.STICKER -> stickerAnswer(binding!!, viewModel!!)
                }
            else ->  // Command
                if (viewModel?.isCreatingCallbackButton == true)
                    when (binding?.spinnerTypeOfCommand?.selectedItem as BotCreator.TypeCallback) {
                        BotCreator.TypeCallback.INLINE -> {
                            if (binding?.inputCommand?.text.isNullOrEmpty())
                                return@OnClickListener
                            child_inlineAnswer(binding!!, viewModel!!)
                        }
                        BotCreator.TypeCallback.REPLY -> {
                            if (binding?.inputCommand?.text.isNullOrEmpty())
                                return@OnClickListener
                            child_replyAnswer(binding!!, viewModel!!)
                        }
                    }
                else
                    when (binding?.spinnerTypeOfCommand?.selectedItem as BotCreator.TypeCommand) {
                        BotCreator.TypeCommand.COMMAND -> {
                            if (binding?.inputCommand?.text.isNullOrEmpty() || binding?.inputCommand?.text.toString()
                                    .contains(' ')
                            )
                                return@OnClickListener
                            child_commandAnswer(binding!!, viewModel!!)
                        }
                        BotCreator.TypeCommand.TEXT -> {
                            if (binding?.inputCommand?.text.isNullOrEmpty())
                                return@OnClickListener
                            child_textAnswer(binding!!, viewModel!!)
                        }
                        BotCreator.TypeCommand.ANIMATION -> child_animationAnswer(binding!!, viewModel!!)
                        BotCreator.TypeCommand.DOCUMENT -> child_documentAnswer(binding!!, viewModel!!)
                        BotCreator.TypeCommand.PHOTO -> child_photoAnswer(binding!!, viewModel!!)
                        BotCreator.TypeCommand.VIDEO -> child_videoAnswer(binding!!, viewModel!!)
                        BotCreator.TypeCommand.VOICE -> child_voiceAnswer(binding!!, viewModel!!)
                        BotCreator.TypeCommand.CONTACT -> child_contactAnswer(binding!!, viewModel!!)
                        BotCreator.TypeCommand.LOCATION -> child_locationAnswer(binding!!, viewModel!!)
                        BotCreator.TypeCommand.VIDEO_NOTE -> child_videoNoteAnswer(binding!!, viewModel!!)
                        BotCreator.TypeCommand.STICKER -> child_stickerAnswer(binding!!, viewModel!!)
                    }
        }

        if (isSuccess)
            viewModel?.updateBot(viewModel?.chosenBot?.saveBot())
    }

    override fun initObservers() {
        viewModel?.updateTrigger?.observe(viewLifecycleOwner) {
            it?.let {
                viewModel?.run {
                    updateTrigger.call()

                    if (choosenCommand > 0) {
                        val id = commandsDeque.peek()?.id ?: -1
                        commandsDeque.pop()
                        commandsDeque.push(chosenBot.findFather(id))
                    }

                    isCreatingCommand = false
                    isCreatingCallbackButton = false

                    Toast.makeText(requireContext(), "Command created", Toast.LENGTH_SHORT).show()
                    router?.exit()
                }
            }
        }
    }

    override fun initStartValues() {
        viewModel?.isCreatingCommand = true
        pickiT = PickiT(requireContext(), this, requireActivity())
    }

    override fun initUI() {
        if (viewModel?.isCreatingCallbackButton == true) {
            binding?.inputCommand?.hint = "button text"
            binding?.txTypeOfCommand?.text = "Type of button:"
            binding?.txCommand?.text = "Text on button:"
            binding?.btCreateCommand?.text = "Create button"
        }
    }

    override fun initButtons() {
        binding?.btCreateCommand?.setOnClickListener(btClickListenerCreatorCommandFragment)
        binding?.btAddSrc?.setOnClickListener {
            when (binding?.spinnerTypeOfAnswer?.selectedItem as BotCreator.TypeAnswer) {
                BotCreator.TypeAnswer.AUDIO -> {
                    openGalleryForSomething("audio/*")
                }
                BotCreator.TypeAnswer.DOCUMENT -> {
                    openGalleryForSomething("application/*")
                }
                BotCreator.TypeAnswer.PHOTO -> {
                    openGalleryForSomething("image/*")
                }
                BotCreator.TypeAnswer.VIDEO -> {
                    openGalleryForSomething("video/*")
                }
                BotCreator.TypeAnswer.VOICE -> {
                    openGalleryForSomething("audio/*")
                }
                BotCreator.TypeAnswer.VIDEO_NOTE -> {
                    openGalleryForSomething("video/*")
                }
                else -> throw Exception("error")
            }
        }
    }

    override fun initSpinners() {
        val adapterCommands = if (viewModel?.isCreatingCallbackButton == true)
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                BotCreator.TypeCallback.values()
            )
        else
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                BotCreator.TypeCommand.values()
            )
        val adapterAnswers = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            BotCreator.TypeAnswer.values()
        )
        binding?.spinnerTypeOfCommand?.apply {
            adapter = adapterCommands
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (viewModel?.isCreatingCallbackButton) {
                        true -> {
                            simpleTextCommand()
                        }
                        false -> {
                            when (BotCreator.TypeCommand.values()[position]) {
                                BotCreator.TypeCommand.COMMAND -> {
                                    simpleTextCommand()
                                }
                                BotCreator.TypeCommand.TEXT -> {
                                    simpleTextCommand()
                                }
                                BotCreator.TypeCommand.ANIMATION -> {
                                    simpleCommand()
                                }
                                BotCreator.TypeCommand.DOCUMENT -> {
                                    simpleCommand()
                                }
                                BotCreator.TypeCommand.PHOTO -> {
                                    simpleCommand()
                                }
                                BotCreator.TypeCommand.VIDEO -> {
                                    simpleCommand()
                                }
                                BotCreator.TypeCommand.VOICE -> {
                                    simpleCommand()
                                }
                                BotCreator.TypeCommand.CONTACT -> {
                                    simpleCommand()
                                }
                                BotCreator.TypeCommand.LOCATION -> {
                                    simpleCommand()
                                }
                                BotCreator.TypeCommand.VIDEO_NOTE -> {
                                    simpleCommand()
                                }
                                BotCreator.TypeCommand.STICKER -> simpleCommand()
                            }
                        }
                        else -> throw Exception()
                    }

                }

                private fun simpleTextCommand() {
                    binding?.apply {
                        btCreateCommand.isEnabled = true
                        txCommand.visibility = View.VISIBLE
                        inputCommand.visibility = View.VISIBLE
                        btCreateCommand.setOnClickListener(btClickListenerCreatorCommandFragment)
                    }
                }

                private fun simpleCommand() {
                    binding?.apply {
                        btCreateCommand.isEnabled = true
                        txCommand.visibility = View.GONE
                        inputCommand.visibility = View.GONE
                        btCreateCommand.setOnClickListener(btClickListenerCreatorCommandFragment)
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    binding?.apply {
                        btCreateCommand.isEnabled = false
                        btCreateCommand.setOnClickListener(null)
                    }
                }

            }
        }
        binding?.spinnerTypeOfAnswer?.apply {
            adapter = adapterAnswers
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (BotCreator.TypeAnswer.values()[position]) {
                        BotCreator.TypeAnswer.TEXT -> {
                            bindView(
                                View.GONE,
                                View.VISIBLE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                "answer",
                                ""
                            )
                        }
                        BotCreator.TypeAnswer.ANIMATION -> {
                            bindView(
                                View.GONE,
                                View.VISIBLE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                "url",
                                ""
                            )
                        }
                        BotCreator.TypeAnswer.AUDIO -> {
                            bindView(
                                View.GONE,
                                View.GONE,
                                View.VISIBLE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                "",
                                "Add audio"
                            )
                        }
                        BotCreator.TypeAnswer.DOCUMENT -> {
                            bindView(
                                View.GONE,
                                View.GONE,
                                View.VISIBLE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                "",
                                "Add document"
                            )
                        }
                        BotCreator.TypeAnswer.PHOTO -> {
                            bindView(
                                View.GONE,
                                View.GONE,
                                View.VISIBLE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                "",
                                "Add image"
                            )
                        }
                        BotCreator.TypeAnswer.VIDEO -> {
                            bindView(
                                View.GONE,
                                View.GONE,
                                View.VISIBLE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                "",
                                "Add video"
                            )
                        }
                        BotCreator.TypeAnswer.VOICE -> {
                            bindView(
                                View.GONE,
                                View.GONE,
                                View.VISIBLE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                "",
                                "Add voice"
                            )
                        }
                        BotCreator.TypeAnswer.CONTACT -> {
                            bindView(
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.VISIBLE,
                                View.VISIBLE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                "",
                                ""
                            )
                        }
                        BotCreator.TypeAnswer.LOCATION -> {
                            bindView(
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.VISIBLE,
                                View.VISIBLE,
                                View.GONE,
                                View.GONE,
                                "",
                                ""
                            )
                        }
                        BotCreator.TypeAnswer.POLL -> {
                            bindView(
                                View.VISIBLE,
                                View.VISIBLE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                "answers (split by \";\")",
                                ""
                            )
                        }
                        BotCreator.TypeAnswer.VENUE -> {
                            bindView(
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.VISIBLE,
                                View.VISIBLE,
                                View.VISIBLE,
                                View.VISIBLE,
                                "",
                                ""
                            )
                        }
                        BotCreator.TypeAnswer.VIDEO_NOTE -> {
                            bindView(
                                View.GONE,
                                View.GONE,
                                View.VISIBLE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                View.GONE,
                                "",
                                "Add video"
                            )
                        }
                    }
                }

                private fun bindView(
                    inputQuestionV: Int,
                    inputAnswerV: Int,
                    btAddSrcV: Int,
                    inputFirstNameV: Int,
                    inputPhoneV: Int,
                    inputLatV: Int,
                    inputLonV: Int,
                    inputAddressV: Int,
                    inputTitleV: Int,
                    inputAnswerH: String,
                    btAddSrcT: String
                ) {
                    binding?.apply {
                        btCreateCommand.isEnabled = true
                        inputQuestion.visibility = inputQuestionV
                        inputAnswer.visibility = inputAnswerV
                        btAddSrc.visibility = btAddSrcV
                        inputFirstName.visibility = inputFirstNameV
                        inputPhone.visibility = inputPhoneV
                        inputLat.visibility = inputLatV
                        inputLon.visibility = inputLonV
                        inputAddress.visibility = inputAddressV
                        inputTitle.visibility = inputTitleV
                        inputAnswer.hint = inputAnswerH
                        btAddSrc.text = btAddSrcT
                        btCreateCommand.setOnClickListener(btClickListenerCreatorCommandFragment)
                    }
                }

                private fun notImplemented() {
                    Toast.makeText(requireContext(), "Not implemented", Toast.LENGTH_SHORT).show()
                    binding?.apply {
                        btCreateCommand.isEnabled = false
                        btCreateCommand.setOnClickListener(null)
                        inputQuestion.visibility = View.GONE
                        inputAnswer.visibility = View.GONE
                        inputFirstName.visibility = View.GONE
                        inputPhone.visibility = View.GONE
                        inputLat.visibility = View.GONE
                        inputLon.visibility = View.GONE
                        inputAddress.visibility = View.GONE
                        inputTitle.visibility = View.GONE
                        btAddSrc.visibility = View.GONE
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    binding?.apply {
                        btCreateCommand.isEnabled = false
                        btCreateCommand.setOnClickListener(null)
                        inputQuestion.visibility = View.GONE
                        inputAnswer.visibility = View.GONE
                        inputFirstName.visibility = View.GONE
                        inputPhone.visibility = View.GONE
                        inputLat.visibility = View.GONE
                        inputLon.visibility = View.GONE
                        inputAddress.visibility = View.GONE
                        inputTitle.visibility = View.GONE
                        btAddSrc.visibility = View.GONE
                    }
                }

            }
        }
    }

    private fun openGalleryForSomething(type: String) {
        val intent = Intent()
        intent.type = type
        intent.action = Intent.ACTION_OPEN_DOCUMENT
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                pickiT!!.getPath(data!!.data!!, Build.VERSION.SDK_INT)
            }
        }
    }

    override fun PickiTonUriReturned() {
    }

    override fun PickiTonStartListener() {
    }

    override fun PickiTonProgressUpdate(progress: Int) {
    }

    override fun PickiTonCompleteListener(
        path: String?,
        wasDriveFile: Boolean,
        wasUnknownProvider: Boolean,
        wasSuccessful: Boolean,
        Reason: String?
    ) {
        if (wasSuccessful) {
            uriSrc = path.toString()
            binding?.btAddSrc?.text = uriSrc.substring(uriSrc.lastIndexOf('/') + 1)
        } else
            Toast.makeText(requireContext(), "Problem with pick file", Toast.LENGTH_SHORT).show()
    }

    override fun PickiTonMultipleCompleteListener(
        paths: ArrayList<String>?,
        wasSuccessful: Boolean,
        Reason: String?
    ) {
    }

}