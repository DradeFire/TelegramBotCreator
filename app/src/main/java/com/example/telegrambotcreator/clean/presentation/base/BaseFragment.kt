package com.example.telegrambotcreator.clean.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.telegrambotcreator.clean.presentation.activity.viewmodel.TelegramViewModel

// TODO: Сделать все методы обязательными (abstract)
// TODO: Вынести логику из фрагментов в их локальные ViewModel-и
abstract class BaseFragment<T : ViewBinding> : Fragment()  {

    abstract val inflater: (LayoutInflater, ViewGroup?, Boolean) -> T
    protected var binding: T? = null
    protected var viewModel: TelegramViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(binding == null) {
            binding = inflater(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(viewModel == null){
            viewModel = ViewModelProvider(requireActivity())[TelegramViewModel::class.java]
        }
        initStartValues()
        initUI()
        initButtons()
        initObservers()
        initSpinners()
        initRecyclerView()
    }

    open fun initRecyclerView(){}

    open fun initStartValues(){}

    open fun initSpinners(){}

    open fun initButtons(){}

    open fun initUI(){}

    open fun initObservers(){}

    override fun onDetach() {
        binding = null
        viewModel = null
        super.onDetach()
    }

}