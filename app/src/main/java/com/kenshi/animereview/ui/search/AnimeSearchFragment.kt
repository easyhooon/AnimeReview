package com.kenshi.animereview.ui.search

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.kenshi.animereview.R
import com.kenshi.animereview.common.collectLatestLifecycleFlow
import com.kenshi.animereview.common.repeatOnStarted
import com.kenshi.animereview.common.showKeyboard
import com.kenshi.animereview.databinding.FragmentSearchBinding
import com.kenshi.animereview.ui.base.BaseFragment
import com.kenshi.animereview.util.Constants.SEARCH_BOOKS_TIME_DELAY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeSearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel: AnimeSearchViewModel by viewModels()

    private val animeSearchAdapter: AnimeSearchPagingAdapter by lazy {
        AnimeSearchPagingAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // viewModel initialize 잊지말자..
        bind {
            vm = viewModel
        }

        initView()
        // initNavigation()
        searchAnime()
        initObserver()

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
    private fun initObserver() {
        collectLatestLifecycleFlow(viewModel.searchPagingResult) {
            animeSearchAdapter.submitData(it)
        }
    }


    private fun handleEvent(event: AnimeSearchViewModel.Event) = when (event) {
        is AnimeSearchViewModel.Event.BackIconClick -> {
            findNavController().navigateUp()
        }
    }

    private fun searchAnime() = with(binding) {
        var startTime = System.currentTimeMillis()
        var endTime: Long

        etSearch.text =
            Editable.Factory.getInstance().newEditable(viewModel.query)

        // 이런식으로 구현자체는 가능하다.
        etSearch.addTextChangedListener { text: Editable? ->
            endTime = System.currentTimeMillis()
            if (endTime - startTime >= SEARCH_BOOKS_TIME_DELAY) {
                text?.let {
                    val query = it.toString().trim()
                    if (query.isNotEmpty()) {
                        viewModel.searchAnime(query)
                        viewModel.query = query
                    }
                }
            }
            startTime = endTime
        }
    }

    private fun initLoadState() = with(binding) {
        //combinedLoadStates -> PagingSource 와 RemoteMediator 두가지 source 의 loading 상태를 가지고 있음
        //remoteMediator 는 사용하지 않기 때문에 source 의 값만 대응하면 됨
        //prepend : loading 시작시 만들어짐
        //append : loading 종료시 만들어짐
        //refresh : loading 값을 갱신할때 만들어짐
        animeSearchAdapter.addLoadStateListener { combinedLoadStates ->
            val loadState = combinedLoadStates.source
            // list가 비어있는지 판정하는 방법
            val isListEmpty = animeSearchAdapter.itemCount < 1
                    && loadState.refresh is LoadState.NotLoading
                    && loadState.append.endOfPaginationReached

            tvEmptylist.isVisible = isListEmpty
            rvSearchResult.isVisible = !isListEmpty

            progressBar.isVisible = loadState.refresh is LoadState.Loading

            //loading State 는 LoadStateAdapter 에서 관리해주기 때문에 주석처리
//            btnRetry.isVisible = loadState.refresh is LoadState.Error
//                    || loadState.append is LoadState.Error
//                    || loadState.prepend is LoadState.Error
//
//            val errorState: LoadState.Error? = loadState.append as? LoadState.Error
//                ?: loadState.prepend as? LoadState.Error
//                ?: loadState.refresh as? LoadState.Error
//            errorState?.let {
//                Toast.makeText(requireContext(), "it.error.message", Toast.LENGTH_SHORT).show()
//            }
//
//            btnRetry.setOnClickListener {
//                bookSearchAdapter.retry()
//            }
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