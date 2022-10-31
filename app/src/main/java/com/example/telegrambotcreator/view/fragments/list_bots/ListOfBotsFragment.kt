package com.example.telegrambotcreator.view.fragments.list_bots

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.telegrambotcreator.databinding.FragmentListOfBotsBinding
import com.example.telegrambotcreator.view.base.BaseFragment
import com.example.telegrambotcreator.view.screens.Screens
import com.example.telegrambotcreator.view.fragments.list_bots.adapter.ListOfBotsAdapter

class ListOfBotsFragment : BaseFragment<FragmentListOfBotsBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListOfBotsBinding =
        FragmentListOfBotsBinding::inflate

    override fun initButtons() = with(binding!!) {
        btCreateBot.setOnClickListener {
            viewModel?.router?.navigateTo(Screens.CreatorBotFrag())
        }
    }

    override fun initObservers() {
        viewModel?.getAllBots()
        viewModel?.allBots?.observe(viewLifecycleOwner) { list ->
            list?.let {
                binding?.rcBots?.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = ListOfBotsAdapter(list, viewModel!!)
                    binding?.progressListOfBots?.visibility = View.GONE
                    visibility = View.VISIBLE
                }
                viewModel?.allBots?.call()
            }

        }
    }

    // Storage Permissions
//    private val REQUEST_EXTERNAL_STORAGE = 1
//    private val PERMISSIONS_STORAGE = arrayOf(
//        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.WRITE_EXTERNAL_STORAGE
//    )
//
//    fun verifyStoragePermissions() {
//        // Check if we have write permission
//        val permission = ActivityCompat.checkSelfPermission(
//            requireActivity(),
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//        )
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            // We don't have permission so prompt the user
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                PERMISSIONS_STORAGE,
//                REQUEST_EXTERNAL_STORAGE
//            )
//        }
//    }

}