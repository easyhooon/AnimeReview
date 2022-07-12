package com.kenshi.animereview.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.common.repeatOnStarted
import com.kenshi.animereview.common.showKeyboard
import com.kenshi.animereview.databinding.FragmentSearchBinding
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AnimeSearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel: AnimeSearchViewModel by viewModels()

//    private val searchAnimeAdapter: SearchAnimeAdapter by lazy {
//        SearchAnimeAdapter { animeInfo ->
//            //navigateToDetail(animeInfo)
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // viewModel initialize 잊지말자..
        bind {
            vm = viewModel
        }

        initView()
        // initNavigation()

        repeatOnStarted {
            viewModel.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun initView() = with(binding) {
        etSearch.apply {
            requestFocus()
            showKeyboard()
        }
    }

    private fun handleEvent(event: AnimeSearchViewModel.Event) = when (event) {
        is AnimeSearchViewModel.Event.BackIconClick -> {
            findNavController().navigateUp()
        }
    }

//    private fun initNavigation() {
//        binding.tbAnimeSearch.setOnClickListener {
//            if (!findNavController().navigateUp())
//                requireActivity().finish()
//            else
//                findNavController().navigateUp()
//        }
//    }
}