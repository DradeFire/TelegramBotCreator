package com.example.telegrambotcreator.view.fragments.modification_command

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.telegrambotcreator.consts.Consts
import com.example.telegrambotcreator.databinding.FragmentModificationBotBinding
import com.example.telegrambotcreator.view.fragments.modification_command.helper.IsNotGoodFunctions.isNotGoodAnimation
import com.example.telegrambotcreator.view.fragments.modification_command.helper.IsNotGoodFunctions.isNotGoodAudio
import com.example.telegrambotcreator.view.fragments.modification_command.helper.IsNotGoodFunctions.isNotGoodContact
import com.example.telegrambotcreator.view.fragments.modification_command.helper.IsNotGoodFunctions.isNotGoodDocument
import com.example.telegrambotcreator.view.fragments.modification_command.helper.IsNotGoodFunctions.isNotGoodLocation
import com.example.telegrambotcreator.view.fragments.modification_command.helper.IsNotGoodFunctions.isNotGoodPhoto
import com.example.telegrambotcreator.view.fragments.modification_command.helper.IsNotGoodFunctions.isNotGoodPoll
import com.example.telegrambotcreator.view.fragments.modification_command.helper.IsNotGoodFunctions.isNotGoodText
import com.example.telegrambotcreator.view.fragments.modification_command.helper.IsNotGoodFunctions.isNotGoodVenue
import com.example.telegrambotcreator.view.fragments.modification_command.helper.IsNotGoodFunctions.isNotGoodVideo
import com.example.telegrambotcreator.view.fragments.modification_command.helper.IsNotGoodFunctions.isNotGoodVoice
import com.example.telegrambotcreator.databinding.FragmentModificationCommandBinding
import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.helper.convertToCallbackType
import com.example.telegrambotcreator.model.creator.helper.convertToType
import com.example.telegrambotcreator.model.creator.helper.findFather
import com.example.telegrambotcreator.model.creator.helper.saveBot
import com.example.telegrambotcreator.model.creator.model.*
import com.example.telegrambotcreator.view.base.BaseFragment
import com.example.telegrambotcreator.viewmodel.TelegramViewModel
import com.hbisoft.pickit.PickiT
import com.hbisoft.pickit.PickiTCallbacks

class ModificationCommandFragment : BaseFragment<FragmentModificationCommandBinding>(),
    PickiTCallbacks {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentModificationCommandBinding =
        FragmentModificationCommandBinding::inflate

    private var pickiT: PickiT? = null
    private var isSuccess = false
    private var uriSrc: String? = null
    internal val btClickListenerModifyCommandFragment = View.OnClickListener {
        viewModel?.chosenBot?.findFather(viewModel?.commandsDeque?.peek()!!.id)?.let {
            when (binding?.spinnerTypeOfAnswer?.selectedItem as BotCreator.TypeAnswer) {
                BotCreator.TypeAnswer.TEXT -> if (!isNotGoodText(binding!!, requireContext())) {
                    addNewData(
                        it, null, null, null, null, null, null, null, null,
                        binding?.inputAnswer?.text.toString(), null
                    )
                    isSuccess = true
                }
                BotCreator.TypeAnswer.ANIMATION -> if (!isNotGoodAnimation(
                        binding!!,
                        requireContext()
                    )
                ) {
                    addNewData(
                        it, null, null, null, null, null, null, null, null,
                        binding?.inputAnswer?.text.toString(), null
                    )
                    isSuccess = true
                }
                BotCreator.TypeAnswer.AUDIO -> if (!isNotGoodAudio(binding!!, requireContext())) {
                    val file = uriSrc ?: viewModel?.commandsDeque?.peek()!!.action?.answerTGFile
                    addNewData(
                        it, file,
                        null, null, null, null, null, null, null, null, null
                    )
                    isSuccess = true
                }
                BotCreator.TypeAnswer.DOCUMENT -> if (!isNotGoodDocument(
                        binding!!,
                        requireContext()
                    )
                ) {
                    val file = uriSrc ?: viewModel?.commandsDeque?.peek()!!.action?.answerTGFile
                    addNewData(
                        it, file,
                        null, null, null, null, null, null, null, null, null
                    )
                    isSuccess = true
                }
                BotCreator.TypeAnswer.PHOTO -> if (!isNotGoodPhoto(binding!!, requireContext())) {
                    val file = uriSrc ?: viewModel?.commandsDeque?.peek()!!.action?.answerTGFile
                    addNewData(
                        it, file,
                        null, null, null, null, null, null, null, null, null
                    )
                    isSuccess = true
                }
                BotCreator.TypeAnswer.VIDEO -> if (!isNotGoodVideo(binding!!, requireContext())) {
                    val file = uriSrc ?: viewModel?.commandsDeque?.peek()!!.action?.answerTGFile
                    addNewData(
                        it, file,
                        null, null, null, null, null, null, null, null, null
                    )
                    isSuccess = true
                }
                BotCreator.TypeAnswer.VOICE -> if (!isNotGoodVoice(binding!!, requireContext())) {
                    val file = uriSrc ?: viewModel?.commandsDeque?.peek()!!.action?.answerTGFile
                    addNewData(
                        it, file,
                        null, null, null, null, null, null, null, null, null
                    )
                    isSuccess = true
                }
                BotCreator.TypeAnswer.CONTACT -> if (!isNotGoodContact(
                        binding!!,
                        requireContext()
                    )
                ) {
                    addNewData(
                        it,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        binding?.inputPhone?.text.toString(),
                        binding?.inputFirstName?.text.toString(),
                        null,
                        null
                    )
                    isSuccess = true
                }
                BotCreator.TypeAnswer.LOCATION -> if (!isNotGoodLocation(
                        binding!!,
                        requireContext()
                    )
                ) {
                    addNewData(
                        it, null, null, null, null,
                        binding?.inputLon?.text.toString().toFloat(),
                        binding?.inputLat?.text.toString().toFloat(),
                        null, null, null, null
                    )
                    isSuccess = true
                }
                BotCreator.TypeAnswer.POLL -> if (!isNotGoodPoll(binding!!, requireContext())) {
                    val pollList = (binding?.inputAnswer?.text.toString().split(';') as ArrayList)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        pollList.removeIf { i -> i.isBlank() || i.isEmpty() }
                    } else {
                        val tmp = arrayListOf<String>()
                        pollList.forEach { i -> if (i.isNotEmpty() && i.isNotBlank()) tmp.add(i) }
                        pollList.addAll(tmp)
                    }
                    addNewData(
                        it, null,
                        binding?.inputQuestion?.text.toString(),
                        null, null, null, null, null, null, null,
                        pollList
                    )
                    isSuccess = true
                }
                BotCreator.TypeAnswer.VENUE -> if (!isNotGoodVenue(binding!!, requireContext())) {
                    addNewData(
                        it, null, null,
                        binding?.inputTitle?.text.toString(),
                        binding?.inputAddress?.text.toString(),
                        binding?.inputLon?.text.toString().toFloat(),
                        binding?.inputLat?.text.toString().toFloat(),
                        null, null, null, null
                    )
                    isSuccess = true
                }
                BotCreator.TypeAnswer.VIDEO_NOTE -> if (!isNotGoodVideo(
                        binding!!,
                        requireContext()
                    )
                ) {
                    val file = uriSrc ?: viewModel?.commandsDeque?.peek()!!.action?.answerTGFile
                    addNewData(
                        it, file,
                        null, null, null, null, null, null, null, null, null
                    )
                    isSuccess = true
                }
            }
            when (it) {
                is CommandTG -> {
                    if (binding?.inputCommand?.text.toString().isNotEmpty()
                        && binding?.inputCommand?.text.toString().isNotBlank()
                        && !binding?.inputCommand?.text.toString().contains(' ')
                    )
                        it.command = binding?.inputCommand?.text.toString()
                    else {
                        isSuccess = false
                        Toast.makeText(
                            requireContext(),
                            "Field \"Command\" is bad",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is TextTG -> {
                    if (binding?.inputCommand?.text.toString()
                            .isNotEmpty() && binding?.inputCommand?.text.toString().isNotBlank()
                    )
                        it.text = binding?.inputCommand?.text.toString()
                    else {
                        isSuccess = false
                        Toast.makeText(
                            requireContext(),
                            "Field \"Command\" is bad",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
                else -> throw Exception()
            }
        }
        if (isSuccess) {
            viewModel?.updateBot(viewModel?.chosenBot?.saveBot())
            isSuccess = false
        }
    }

    @SuppressLint("SetTextI18n")
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

    override fun initObservers() {
        viewModel?.updateTrigger?.observe(viewLifecycleOwner) {
            it?.let {
                val idCom = viewModel?.commandsDeque?.peek()?.id ?: -1
                viewModel?.commandsDeque?.pop()
                viewModel?.commandsDeque?.push(viewModel?.chosenBot?.findFather(idCom))

                viewModel?.isCreatingCommand = false
                viewModel?.isCreatingCallbackButton = false
                viewModel?.updateTrigger?.call()
                Toast.makeText(requireContext(), "Command edited", Toast.LENGTH_SHORT).show()
                viewModel?.router?.exit()
            }
        }
    }

    override fun initButtons() {
        binding?.btAddSrc?.setOnClickListener {
            when (binding?.spinnerTypeOfAnswer?.selectedItem as BotCreator.TypeAnswer) {
                BotCreator.TypeAnswer.AUDIO -> openGalleryForSomething("audio/*")
                BotCreator.TypeAnswer.DOCUMENT -> openGalleryForSomething("application/*")
                BotCreator.TypeAnswer.PHOTO -> openGalleryForSomething("image/*")
                BotCreator.TypeAnswer.VIDEO -> openGalleryForSomething("video/*")
                BotCreator.TypeAnswer.VOICE -> openGalleryForSomething("audio/*")
                BotCreator.TypeAnswer.VIDEO_NOTE -> openGalleryForSomething("video/*")
                else -> throw java.lang.Exception("error")
            }
        }
        binding?.btCreateCommand?.setOnClickListener(btClickListenerModifyCommandFragment)
    }

    override fun initSpinners() {
        val posCommand = when (viewModel?.commandsDeque?.peek()) {
            is CommandTG -> 0
            is AnimationTG -> 2
            is ContactTG -> 7
            is DocumentTG -> 3
            is LocationTG -> 8
            is PhotoTG -> 4
            is StickerTG -> 10
            is TextTG -> 1
            is VideoNoteTG -> 9
            is VideoTG -> 5
            is VoiceTG -> 6
            is CallBackTG -> when ((viewModel?.commandsDeque?.peek() as CallBackTG).typeCallback.convertToCallbackType()) {
                BotCreator.TypeCallback.INLINE -> 0
                BotCreator.TypeCallback.REPLY -> 1
            }
            else -> throw Exception("")
        }
        when (viewModel?.commandsDeque?.peek()?.typeAnswer?.convertToType()) {
            BotCreator.TypeAnswer.TEXT -> {
                bindSpecView(
                    View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE,
                    View.GONE, View.GONE, View.GONE, "answer", ""
                )
                binding?.inputAnswer?.setText(viewModel?.commandsDeque?.peek()?.action?.answerText)
            }
            BotCreator.TypeAnswer.ANIMATION -> {
                bindSpecView(
                    View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE,
                    View.GONE, View.GONE, View.GONE, "url", ""
                )
                binding?.inputAnswer?.setText(viewModel?.commandsDeque?.peek()?.action?.answerText)
            }
            BotCreator.TypeAnswer.AUDIO -> {
                bindSpecView(
                    View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE,
                    View.GONE, View.GONE, View.GONE, "", "Add audio"
                )
                binding?.btAddSrc?.text =
                    viewModel?.commandsDeque?.peek()?.action?.answerTGFile!!.substring(
                        viewModel?.commandsDeque?.peek()?.action?.answerTGFile!!.lastIndexOf('/') + 1
                    )
            }
            BotCreator.TypeAnswer.DOCUMENT -> {
                bindSpecView(
                    View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE,
                    View.GONE, View.GONE, View.GONE, "", "Add document"
                )
                binding?.btAddSrc?.text =
                    viewModel?.commandsDeque?.peek()?.action?.answerTGFile!!.substring(
                        viewModel?.commandsDeque?.peek()?.action?.answerTGFile!!.lastIndexOf('/') + 1
                    )
            }
            BotCreator.TypeAnswer.PHOTO -> {
                bindSpecView(
                    View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE,
                    View.GONE, View.GONE, View.GONE, "", "Add image"
                )
                binding?.btAddSrc?.text =
                    viewModel?.commandsDeque?.peek()?.action?.answerTGFile!!.substring(
                        viewModel?.commandsDeque?.peek()?.action?.answerTGFile!!.lastIndexOf('/') + 1
                    )
            }
            BotCreator.TypeAnswer.VIDEO -> {
                bindSpecView(
                    View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE,
                    View.GONE, View.GONE, View.GONE, "", "Add video"
                )
                binding?.btAddSrc?.text =
                    viewModel?.commandsDeque?.peek()?.action?.answerTGFile!!.substring(
                        viewModel?.commandsDeque?.peek()?.action?.answerTGFile!!.lastIndexOf('/') + 1
                    )
            }
            BotCreator.TypeAnswer.VOICE -> {
                bindSpecView(
                    View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE,
                    View.GONE, View.GONE, View.GONE, "", "Add voice"
                )
                binding?.btAddSrc?.text =
                    viewModel?.commandsDeque?.peek()?.action?.answerTGFile!!.substring(
                        viewModel?.commandsDeque?.peek()?.action?.answerTGFile!!.lastIndexOf('/') + 1
                    )
            }
            BotCreator.TypeAnswer.CONTACT -> {
                bindSpecView(
                    View.GONE, View.GONE, View.GONE, View.VISIBLE, View.VISIBLE, View.GONE,
                    View.GONE, View.GONE, View.GONE, "", ""
                )
                binding?.apply {
                    inputFirstName.setText(viewModel?.commandsDeque?.peek()?.action?.firstName)
                    inputPhone.setText(viewModel?.commandsDeque?.peek()?.action?.phoneNumber)
                }
            }
            BotCreator.TypeAnswer.LOCATION -> {
                bindSpecView(
                    View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.VISIBLE,
                    View.VISIBLE, View.GONE, View.GONE, "", ""
                )
                binding?.apply {
                    inputLat.setText(viewModel?.commandsDeque?.peek()?.action?.lat.toString())
                    inputLon.setText(viewModel?.commandsDeque?.peek()?.action?.lon.toString())
                }
            }
            BotCreator.TypeAnswer.POLL -> {
                bindSpecView(
                    View.VISIBLE, View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE,
                    View.GONE, View.GONE, View.GONE, "answers (split by \";\")", ""
                )
                binding?.inputQuestion?.setText(viewModel?.commandsDeque?.peek()?.action?.question)
                var t = ""
                viewModel?.commandsDeque?.peek()?.action?.pollList?.forEachIndexed { index, s ->
                    t += s
                    if (index != viewModel?.commandsDeque?.peek()?.action?.pollList!!.lastIndex)
                        t += ';'
                }
                binding?.inputAnswer?.setText(t)
            }
            BotCreator.TypeAnswer.VENUE -> {
                bindSpecView(
                    View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.VISIBLE,
                    View.VISIBLE, View.VISIBLE, View.VISIBLE, "", ""
                )
                binding?.apply {
                    inputLat.setText(viewModel?.commandsDeque?.peek()?.action?.lat.toString())
                    inputLon.setText(viewModel?.commandsDeque?.peek()?.action?.lon.toString())
                    inputAddress.setText(viewModel?.commandsDeque?.peek()?.action?.address)
                    inputTitle.setText(viewModel?.commandsDeque?.peek()?.action?.title)
                }
            }
            BotCreator.TypeAnswer.VIDEO_NOTE -> {
                bindSpecView(
                    View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE,
                    View.GONE, View.GONE, View.GONE, "", "Add video"
                )
                binding?.btAddSrc?.text =
                    viewModel?.commandsDeque?.peek()?.action?.answerTGFile!!.substring(
                        viewModel?.commandsDeque?.peek()?.action?.answerTGFile!!.lastIndexOf('/') + 1
                    )
            }
            else -> throw Exception()
        }
        val adapterCommands = if (viewModel?.isCreatingCallbackButton == true)
            ArrayAdapter(
                requireContext(), android.R.layout.simple_spinner_dropdown_item,
                BotCreator.TypeCallback.values()
            )
        else
            ArrayAdapter(
                requireContext(), android.R.layout.simple_spinner_dropdown_item,
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
                                BotCreator.TypeCommand.COMMAND -> simpleTextCommand()
                                BotCreator.TypeCommand.TEXT -> simpleTextCommand()
                                BotCreator.TypeCommand.ANIMATION -> simpleCommand()
                                BotCreator.TypeCommand.DOCUMENT -> simpleCommand()
                                BotCreator.TypeCommand.PHOTO -> simpleCommand()
                                BotCreator.TypeCommand.VIDEO -> simpleCommand()
                                BotCreator.TypeCommand.VOICE -> simpleCommand()
                                BotCreator.TypeCommand.CONTACT -> simpleCommand()
                                BotCreator.TypeCommand.LOCATION -> simpleCommand()
                                BotCreator.TypeCommand.VIDEO_NOTE -> simpleCommand()
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
                        inputCommand.setText((viewModel?.commandsDeque?.peek() as CommandTG).command)
                        btCreateCommand.setOnClickListener(btClickListenerModifyCommandFragment)
                    }
                }

                private fun simpleCommand() {
                    binding?.apply {
                        btCreateCommand.isEnabled = true
                        txCommand.visibility = View.GONE
                        inputCommand.visibility = View.GONE
                        btCreateCommand.setOnClickListener(btClickListenerModifyCommandFragment)
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    binding?.apply {
                        btCreateCommand.isEnabled = false
                        btCreateCommand.setOnClickListener(null)
                    }
                }

            }
            setSelection(posCommand)
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
                        BotCreator.TypeAnswer.TEXT -> bindView(
                            View.GONE, View.VISIBLE, View.GONE, View.GONE,
                            View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "answer", ""
                        )
                        BotCreator.TypeAnswer.ANIMATION -> bindView(
                            View.GONE, View.VISIBLE, View.GONE, View.GONE,
                            View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "url", ""
                        )
                        BotCreator.TypeAnswer.AUDIO -> bindView(
                            View.GONE, View.GONE, View.VISIBLE, View.GONE,
                            View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "", "Add audio"
                        )
                        BotCreator.TypeAnswer.DOCUMENT -> bindView(
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
                        BotCreator.TypeAnswer.PHOTO -> bindView(
                            View.GONE, View.GONE, View.VISIBLE, View.GONE,
                            View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "", "Add image"
                        )
                        BotCreator.TypeAnswer.VIDEO -> bindView(
                            View.GONE, View.GONE, View.VISIBLE, View.GONE,
                            View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "", "Add video"
                        )
                        BotCreator.TypeAnswer.VOICE -> bindView(
                            View.GONE, View.GONE, View.VISIBLE, View.GONE,
                            View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "", "Add voice"
                        )
                        BotCreator.TypeAnswer.CONTACT -> bindView(
                            View.GONE, View.GONE, View.GONE, View.VISIBLE,
                            View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE, "", ""
                        )
                        BotCreator.TypeAnswer.LOCATION -> bindView(
                            View.GONE, View.GONE, View.GONE, View.GONE,
                            View.GONE, View.VISIBLE, View.VISIBLE, View.GONE, View.GONE, "", ""
                        )
                        BotCreator.TypeAnswer.POLL -> bindView(
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
                        BotCreator.TypeAnswer.VENUE -> bindView(
                            View.GONE, View.GONE, View.GONE, View.GONE, View.GONE,
                            View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, "", ""
                        )
                        BotCreator.TypeAnswer.VIDEO_NOTE -> bindView(
                            View.GONE, View.GONE, View.VISIBLE, View.GONE,
                            View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "", "Add video"
                        )
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
                ) = with(binding!!) {
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
                    btCreateCommand.setOnClickListener(btClickListenerModifyCommandFragment)
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

    private fun addNewData(
        it: ListenerTgBase, file: String?, question: String?, title: String?, address: String?,
        lon: Float?, lat: Float?, phoneNumber: String?, firstName: String?, answerText: String?,
        arrayList: ArrayList<String>?,
    ) {
        it.action?.answerTGFile = file
        it.action?.question = question
        it.action?.title = title
        it.action?.address = address
        it.action?.lon = lon
        it.action?.lat = lat
        it.action?.phoneNumber = phoneNumber
        it.action?.firstName = firstName
        it.action?.answerText = answerText
        it.action?.pollList = arrayList
    }

    private fun bindSpecView(
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
    ) = with(binding!!) {
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
        btCreateCommand.setOnClickListener(btClickListenerModifyCommandFragment)
    }

    private fun openGalleryForSomething(type: String) {
        val intent = Intent()
        intent.type = type
        intent.action = Intent.ACTION_OPEN_DOCUMENT
        startActivityForResult(intent, Consts.REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Consts.REQUEST_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                pickiT!!.getPath(data!!.data!!, Build.VERSION.SDK_INT)
            }
        }
    }

    override fun PickiTonUriReturned() {}

    override fun PickiTonStartListener() {}

    override fun PickiTonProgressUpdate(progress: Int) {}

    override fun PickiTonCompleteListener(
        path: String?, wasDriveFile: Boolean,
        wasUnknownProvider: Boolean, wasSuccessful: Boolean, Reason: String?
    ) {
        if (wasSuccessful) {
            uriSrc = path
            binding?.btAddSrc?.text =
                uriSrc.toString().substring(uriSrc.toString().lastIndexOf('/') + 1)
        } else
            Toast.makeText(requireContext(), "Problem with pick file", Toast.LENGTH_SHORT).show()
    }

    override fun PickiTonMultipleCompleteListener(
        paths: ArrayList<String>?,
        wasSuccessful: Boolean,
        Reason: String?
    ) {}

    override fun onDetach() {
        pickiT = null
        super.onDetach()
    }

}