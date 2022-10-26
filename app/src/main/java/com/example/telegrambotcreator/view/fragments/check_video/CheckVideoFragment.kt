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

    private var binding: FragmentCheckVideoBinding? = null
    private var viewModel: TelegramViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckVideoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[TelegramViewModel::class.java].also {
            it.isCreatingCommand = true
        }

        binding?.videoView?.apply {
            setVideoPath(viewModel?.commandsDeque?.peek()?.answerTGFile)
            setMediaController(MediaController(requireContext()).apply {
                setAnchorView(binding?.videoView)
            })
        }
    }

    override fun onDestroyView() {
        viewModel?.isCreatingCommand = false
        viewModel = null
        binding = null

        super.onDestroyView()
    }

}