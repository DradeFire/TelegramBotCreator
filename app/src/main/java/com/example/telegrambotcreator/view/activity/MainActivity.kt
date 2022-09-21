package com.example.telegrambotcreator.view.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.telegrambotcreator.consts.Consts.REQUEST_CODE
import com.example.telegrambotcreator.databinding.ActivityMainBinding
import com.example.telegrambotcreator.model.creator.BotCreator
import com.example.telegrambotcreator.model.creator.helper.addChildCommandListener
import com.example.telegrambotcreator.model.creator.helper.addCommandListener
import com.example.telegrambotcreator.model.creator.helper.saveBot
import com.example.telegrambotcreator.model.repository.Repository
import com.example.telegrambotcreator.view.cicerone.App
import com.example.telegrambotcreator.view.cicerone.screens.Screens
import com.example.telegrambotcreator.viewmodel.TelegramViewModel
import com.github.terrakok.cicerone.androidx.AppNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var navigator: AppNavigator
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TelegramViewModel

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.INSTANCE.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        App.INSTANCE.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigator = AppNavigator(this, binding.fragContainer.id)
        viewModel = ViewModelProvider(this)[TelegramViewModel::class.java]
        viewModel.initDatabase(this)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)

//        addTestBots()
//        testBotPhoto()
        App.INSTANCE.router.newRootScreen(Screens.ListOfBotsFrag())
    }

    private fun testBotPhoto() {
        openGalleryForSomething("application/*")
    }

    private fun openGalleryForSomething(type: String) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = type
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onBackPressed() {
        if(viewModel.choosenCommand > 0 && !viewModel.isCreatingCommand) {
            viewModel.apply {
                choosenCommand--
                commandsDeque.pop()
            }
            App.INSTANCE.router.exit()
        } else {
            viewModel.apply {
                isCreatingCommand = false
                isCreatingCallbackButton = false
            }
            App.INSTANCE.router.exit()
        }
    }

    private fun addTestBots() {
        CoroutineScope(Dispatchers.Default).launch{
            val db = Repository().getDatabase(this@MainActivity)
            Repository().deleteAll(db)
            val bot1 = Repository().getBotCreator().apply {
                createBaseBot(name = "kek_bot1", description = "123")
                addCommandListener(command = "start", answerText = "ok", typeAnswer = BotCreator.TypeAnswer.TEXT)
                addCommandListener(command = "no start", answerText = "ok", typeAnswer = BotCreator.TypeAnswer.TEXT)
                val ids = getIDs()
                addChildCommandListener("lol_rus", ids.last(), BotCreator.TypeAnswer.TEXT, "Ну дурачок, правда")
                addChildCommandListener("_rus", ids.last(), BotCreator.TypeAnswer.TEXT, "Н, правда")
            }
            val bot2 = Repository().getBotCreator().apply {
                createBaseBot(name = "kek_bot2", description = "123")
                addCommandListener(command = "no start", answerText = "ok", typeAnswer = BotCreator.TypeAnswer.TEXT)
                addCommandListener(command = "start", answerText = "ok", typeAnswer = BotCreator.TypeAnswer.TEXT)
                val ids = getIDs()
                addChildCommandListener("lol_rus", ids.last(), BotCreator.TypeAnswer.TEXT, "Ну дурачок, правда")
            }

            Repository().apply {
                addBot(db, bot1.saveBot())
                addBot(db, bot2.saveBot())
            }
        }
    }

}