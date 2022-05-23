package com.kenshi.animereview.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.MockAnimeInfo
import com.kenshi.animereview.databinding.FragmentHomeBinding
import com.kenshi.animereview.ui.base.BaseFragment
import com.kenshi.animereview.ui.home.HomeFragment.Companion.ANIME_INFO
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    companion object {
        const val ANIME_INFO = "animeInfo"
    }

    private val viewModel: HomeViewModel by viewModels()

    private val mockAnimeAdapter: MockAnimeAdapter by lazy {
        Timber.d("adapter initialized")
        MockAnimeAdapter { animeInfo ->
            mockNavigateToDetail(animeInfo)
        }
    }

    private val animeAdapter: RecommendAnimeAdapter by lazy {
        Timber.d("adapter initialized")
        RecommendAnimeAdapter { animeInfo ->
            navigateToDetail(animeInfo)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("onCreate").d("HomeFragment")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.tag("onViewCreated").d("HomeFragment:%s", this.hashCode())

        //val array: List<Int> = listOf( 1, 2, 3, 4, 5)

        bind {
            vm = viewModel
            mockAdapter = mockAnimeAdapter
            adapter = animeAdapter
        }
    }

    private fun mockNavigateToDetail(animeInfo: MockAnimeInfo) {
        findNavController().navigate(
            R.id.action_navigation_home_to_animeDetailFragment, bundleOf(
                ANIME_INFO to animeInfo
            )
        )
    }

    private fun navigateToDetail(animeInfo: AnimeInfo) {
        findNavController().navigate(
            R.id.action_navigation_home_to_animeDetailFragment, bundleOf(
                ANIME_INFO to animeInfo
            )
        )
    }

//    // 계속 반복되는 함수이므로 재사용하기 위한 모듈화
//    private fun <T> collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                flow.collectLatest(collect)
//            }
//        }
//    }
}