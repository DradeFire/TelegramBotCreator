package com.example.telegrambotcreator.view.fragments.check_video

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.MediaController
import com.example.telegrambotcreator.databinding.FragmentCheckVideoBinding
import com.example.telegrambotcreator.view.base.BaseFragment

class CheckVideoFragment : BaseFragment<FragmentCheckVideoBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCheckVideoBinding =
        FragmentCheckVideoBinding::inflate

    override fun initUI() {
        viewModel?.isCreatingCommand = true

        binding?.videoView?.apply {
            setVideoPath(viewModel?.commandsDeque?.peek()?.action?.answerTGFile)
            setMediaController(MediaController(requireContext()).apply {
                setAnchorView(binding?.videoView)
            })
        }
    }

}