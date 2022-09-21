package com.example.telegrambotcreator.view.fragments.check_video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.lifecycle.ViewModelProvider
import com.example.telegrambotcreator.databinding.FragmentCheckVideoBinding
import com.example.telegrambotcreator.viewmodel.TelegramViewModel

class CheckVideoFragment : Fragment() {

    private lateinit var binding: FragmentCheckVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckVideoBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(requireActivity())[TelegramViewModel::class.java].also {
            it.isCreatingCommand = true
        }

        binding.videoView.apply {
            setVideoPath(viewModel.commandsDeque.peek()!!.answerTGFile)
            setMediaController(MediaController(requireContext()).apply {
                setAnchorView(binding.videoView)
            })
        }

        return binding.root
    }

    override fun onDestroyView() {
        ViewModelProvider(requireActivity())[TelegramViewModel::class.java].also {
            it.isCreatingCommand = false
        }
        super.onDestroyView()
    }

}