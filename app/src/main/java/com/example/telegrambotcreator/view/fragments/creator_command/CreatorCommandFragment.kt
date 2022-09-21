package com.example.telegrambotcreator.view.fragments.creator_command

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
import com.example.telegrambotcreator.consts.Consts.REQUEST_CODE
import com.example.telegrambotcreator.databinding.FragmentCreatorCommandBinding
import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.helper.findFather
import com.example.telegrambotcreator.model.creator.helper.saveBot
import com.example.telegrambotcreator.view.cicerone.App
import com.example.telegrambotcreator.view.fragments.creator_command.helper.*
import com.example.telegrambotcreator.viewmodel.TelegramViewModel
import com.hbisoft.pickit.PickiT
import com.hbisoft.pickit.PickiTCallbacks
import java.lang.Exception

class CreatorCommandFragment : Fragment(), PickiTCallbacks {

    private var pickiT: PickiT? = null
    internal var isSuccess = false
    internal lateinit var uriSrc: String
    internal lateinit var bindingCreatorCommandFragment: FragmentCreatorCommandBinding
    internal lateinit var viewModelCreatorCommandFragment: TelegramViewModel
    internal val btClickListenerCreatorCommandFragment = View.OnClickListener {
        val cur = if(viewModelCreatorCommandFragment.commandsDeque.isEmpty())
                null
            else
                viewModelCreatorCommandFragment.commandsDeque.peek()

        when(cur){
            null -> // Bot
                when(bindingCreatorCommandFragment.spinnerTypeOfCommand.selectedItem as BotCreator.TypeCommand) {
                    BotCreator.TypeCommand.COMMAND -> {
                        if(bindingCreatorCommandFragment.inputCommand.text.isNullOrEmpty() || bindingCreatorCommandFragment.inputCommand.text.contains(' '))
                            return@OnClickListener
                        commandAnswer()
                    }
                    BotCreator.TypeCommand.TEXT -> {
                        if(bindingCreatorCommandFragment.inputCommand.text.isNullOrEmpty())
                            return@OnClickListener
                        textAnswer()
                    }
                    BotCreator.TypeCommand.ANIMATION -> animationAnswer()
                    BotCreator.TypeCommand.DOCUMENT -> documentAnswer()
                    BotCreator.TypeCommand.PHOTO -> photoAnswer()
                    BotCreator.TypeCommand.VIDEO -> videoAnswer()
                    BotCreator.TypeCommand.VOICE -> voiceAnswer()
                    BotCreator.TypeCommand.CONTACT -> contactAnswer()
                    BotCreator.TypeCommand.LOCATION -> locationAnswer()
                    BotCreator.TypeCommand.VIDEO_NOTE -> videoNoteAnswer()
                    BotCreator.TypeCommand.STICKER -> stickerAnswer()
                }
            else ->  // Command
                if (viewModelCreatorCommandFragment.isCreatingCallbackButton)
                    when(bindingCreatorCommandFragment.spinnerTypeOfCommand.selectedItem as BotCreator.TypeCallback){
                        BotCreator.TypeCallback.INLINE -> {
                            if(bindingCreatorCommandFragment.inputCommand.text.isNullOrEmpty())
                                return@OnClickListener
                            child_inlineAnswer()
                        }
                        BotCreator.TypeCallback.REPLY -> {
                            if(bindingCreatorCommandFragment.inputCommand.text.isNullOrEmpty())
                                return@OnClickListener
                            child_replyAnswer()
                        }
                    }
                else
                    when(bindingCreatorCommandFragment.spinnerTypeOfCommand.selectedItem as BotCreator.TypeCommand) {
                        BotCreator.TypeCommand.COMMAND -> {
                            if(bindingCreatorCommandFragment.inputCommand.text.isNullOrEmpty() || bindingCreatorCommandFragment.inputCommand.text.contains(' '))
                                return@OnClickListener
                            child_commandAnswer()
                        }
                        BotCreator.TypeCommand.TEXT -> {
                            if(bindingCreatorCommandFragment.inputCommand.text.isNullOrEmpty())
                                return@OnClickListener
                            child_textAnswer()
                        }
                        BotCreator.TypeCommand.ANIMATION -> child_animationAnswer()
                        BotCreator.TypeCommand.DOCUMENT -> child_documentAnswer()
                        BotCreator.TypeCommand.PHOTO -> child_photoAnswer()
                        BotCreator.TypeCommand.VIDEO -> child_videoAnswer()
                        BotCreator.TypeCommand.VOICE -> child_voiceAnswer()
                        BotCreator.TypeCommand.CONTACT -> child_contactAnswer()
                        BotCreator.TypeCommand.LOCATION -> child_locationAnswer()
                        BotCreator.TypeCommand.VIDEO_NOTE -> child_videoNoteAnswer()
                        BotCreator.TypeCommand.STICKER -> child_stickerAnswer()
                    }
        }

        if(isSuccess)
            viewModelCreatorCommandFragment.updateBot(viewModelCreatorCommandFragment.chosenBot.saveBot())
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initStartVars(inflater, container)

        if(viewModelCreatorCommandFragment.isCreatingCallbackButton){
            bindingCreatorCommandFragment.inputCommand.hint = "button text"
            bindingCreatorCommandFragment.txTypeOfCommand.text = "Type of button:"
            bindingCreatorCommandFragment.txCommand.text = "Text on button:"
            bindingCreatorCommandFragment.btCreateCommand.text = "Create button"
        }

        bindSpinners()
        bindButton()

        return bindingCreatorCommandFragment.root
    }

    private fun initStartVars(inflater: LayoutInflater, container: ViewGroup?) {
        bindingCreatorCommandFragment = FragmentCreatorCommandBinding.inflate(inflater, container, false)
        viewModelCreatorCommandFragment = ViewModelProvider(requireActivity())[TelegramViewModel::class.java]
        viewModelCreatorCommandFragment.isCreatingCommand = true
        viewModelCreatorCommandFragment.updateTrigger.observe(viewLifecycleOwner){
            it?.let {
                viewModelCreatorCommandFragment.apply {
                    if (choosenCommand > 0){
                        val id = viewModelCreatorCommandFragment.commandsDeque.peek().id
                        commandsDeque.pop()
                        commandsDeque.push(viewModelCreatorCommandFragment.chosenBot.findFather(id))
                    }
                }
                viewModelCreatorCommandFragment.isCreatingCommand = false
                viewModelCreatorCommandFragment.isCreatingCallbackButton = false
                viewModelCreatorCommandFragment.updateTrigger.call()
                Toast.makeText(requireContext(), "Command created", Toast.LENGTH_SHORT).show()
                App.INSTANCE.router.exit()
            }

        }
        pickiT = PickiT(requireContext(), this, requireActivity())
    }

    private fun bindButton() {
        bindingCreatorCommandFragment.btCreateCommand.setOnClickListener(btClickListenerCreatorCommandFragment)
        bindingCreatorCommandFragment.btAddSrc.setOnClickListener {
            when(bindingCreatorCommandFragment.spinnerTypeOfAnswer.selectedItem as BotCreator.TypeAnswer){
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

    private fun bindSpinners() {
        val adapterCommands = if (viewModelCreatorCommandFragment.isCreatingCallbackButton)
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, BotCreator.TypeCallback.values())
        else
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, BotCreator.TypeCommand.values())
        val adapterAnswers = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, BotCreator.TypeAnswer.values())
        bindingCreatorCommandFragment.spinnerTypeOfCommand.apply {
            this.adapter = adapterCommands
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when(viewModelCreatorCommandFragment.isCreatingCallbackButton){
                        true -> {
                            simpleTextCommand()
                        }
                        false -> {
                            when(BotCreator.TypeCommand.values()[position]){
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
                    }

                }

                private fun simpleTextCommand() {
                    bindingCreatorCommandFragment.apply {
                        btCreateCommand.isEnabled = true
                        txCommand.visibility = View.VISIBLE
                        inputCommand.visibility = View.VISIBLE
                        btCreateCommand.setOnClickListener(btClickListenerCreatorCommandFragment)
                    }
                }

                private fun simpleCommand(){
                    bindingCreatorCommandFragment.apply {
                        btCreateCommand.isEnabled = true
                        txCommand.visibility = View.GONE
                        inputCommand.visibility = View.GONE
                        btCreateCommand.setOnClickListener(btClickListenerCreatorCommandFragment)
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    bindingCreatorCommandFragment.apply {
                        btCreateCommand.isEnabled = false
                        btCreateCommand.setOnClickListener(null)
                    }
                }

            }
        }
        bindingCreatorCommandFragment.spinnerTypeOfAnswer.apply {
            this.adapter = adapterAnswers
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when(BotCreator.TypeAnswer.values()[position]){
                        BotCreator.TypeAnswer.TEXT -> {
                            bindView(View.GONE, View.VISIBLE,View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "answer", "")
                        }
                        BotCreator.TypeAnswer.ANIMATION -> {
                            bindView(View.GONE, View.VISIBLE,View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "url", "")
                        }
                        BotCreator.TypeAnswer.AUDIO -> {
                            bindView(View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "", "Add audio")
                        }
                        BotCreator.TypeAnswer.DOCUMENT -> {
                            bindView(View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "", "Add document")
                        }
                        BotCreator.TypeAnswer.PHOTO -> {
                            bindView(View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "", "Add image")
                        }
                        BotCreator.TypeAnswer.VIDEO -> {
                            bindView(View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "", "Add video")
                        }
                        BotCreator.TypeAnswer.VOICE -> {
                            bindView(View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "", "Add voice")
                        }
                        BotCreator.TypeAnswer.CONTACT -> {
                            bindView(View.GONE, View.GONE, View.GONE, View.VISIBLE, View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE, "", "")
                        }
                        BotCreator.TypeAnswer.LOCATION -> {
                            bindView(View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.VISIBLE, View.VISIBLE, View.GONE, View.GONE, "", "")
                        }
                        BotCreator.TypeAnswer.POLL -> {
                            bindView(View.VISIBLE, View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "answers (split by \";\")", "")
                        }
                        BotCreator.TypeAnswer.VENUE -> {
                            bindView(View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, "", "")
                        }
                        BotCreator.TypeAnswer.VIDEO_NOTE -> {
                            bindView(View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, "", "Add video")
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
                ) = with(bindingCreatorCommandFragment){
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

                private fun notImplemented(){
                    Toast.makeText(requireContext(), "Not implemented", Toast.LENGTH_SHORT).show()
                    bindingCreatorCommandFragment.apply{
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
                    bindingCreatorCommandFragment.apply{
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
        if(wasSuccessful){
            uriSrc = path!!
            bindingCreatorCommandFragment.btAddSrc.text = path.toString().substring(path.lastIndexOf('/') + 1)
        } else
            Toast.makeText(requireContext(), "Problem with pick file", Toast.LENGTH_SHORT).show()
    }

    override fun PickiTonMultipleCompleteListener(
        paths: ArrayList<String>?,
        wasSuccessful: Boolean,
        Reason: String?
    ) {

    }

    override fun onDestroyView() {
        pickiT = null
        super.onDestroyView()
    }

}