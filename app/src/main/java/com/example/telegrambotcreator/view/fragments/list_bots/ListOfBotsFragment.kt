package com.example.telegrambotcreator.view.fragments.list_bots

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.telegrambotcreator.databinding.FragmentListOfBotsBinding
import com.example.telegrambotcreator.view.cicerone.App
import com.example.telegrambotcreator.view.cicerone.screens.Screens
import com.example.telegrambotcreator.view.fragments.list_bots.adapter.ListOfBotsAdapter
import com.example.telegrambotcreator.viewmodel.TelegramViewModel


class ListOfBotsFragment : Fragment() {

    private lateinit var binding: FragmentListOfBotsBinding
    private lateinit var viewModel: TelegramViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initStartVars(inflater, container)
        initRcView()
        bindButtons()
//        openGalleryForSomething("image/*")

        return binding.root
    }

    private fun initStartVars(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentListOfBotsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[TelegramViewModel::class.java]
    }

    private fun bindButtons() = with(binding) {
        btCreateBot.setOnClickListener {
            App.INSTANCE.router.navigateTo(Screens.CreatorBotFrag())
        }
    }

    private fun initRcView() {
        viewModel.getAllBots()
        viewModel.allBots.observe(viewLifecycleOwner) { list ->
            list?.let {
                binding.rcBots.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = ListOfBotsAdapter(list, viewModel)
                    binding.progressListOfBots.visibility = View.GONE
                    visibility = View.VISIBLE
                }
                viewModel.allBots.call()
            }

        }
    }

//    private fun openGalleryForSomething(type: String) {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = type
//        startActivityForResult(intent, Consts.REQUEST_CODE)
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK && requestCode == Consts.REQUEST_CODE){
//            verifyStoragePermissions()
//
//            val f = File(getUriRealPath(requireContext(), data!!.data!!)) //File((data!!.data!!.path!!.replace("content", "file").substring(10)))
//
////            val uri = Uri.Builder().path(f).build()
//            Glide.with(requireContext())
//                .load(f)
//                .into(binding.imTest)
//        }
//    }

    // Storage Permissions
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun verifyStoragePermissions() {
        // Check if we have write permission
        val permission = ActivityCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                requireActivity(),
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
        }
    }

}