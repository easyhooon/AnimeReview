package com.kenshi.animereview.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.kenshi.animereview.R
import com.kenshi.animereview.common.repeatOnStarted
import com.kenshi.animereview.common.safeNavigate
import com.kenshi.animereview.data.model.kitsu.KitsuAnimeInfo
import com.kenshi.animereview.databinding.FragmentHomeBinding
import com.kenshi.animereview.ui.anime_review.AnimeReviewActivity
import com.kenshi.animereview.ui.base.BaseFragment
import com.kenshi.animereview.util.Constants.ANIME_INFO
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    lateinit var firebaseAuth: FirebaseAuth

    private val viewModel: HomeViewModel by viewModels()

    //TODO ConcatAdapter 고민, viewtype 나누어 하나의 어댑터로..
    private val recommendAnimeAdapter: RecommendAnimeAdapter by lazy {
        RecommendAnimeAdapter { animeInfo ->
            navigateToDetail(animeInfo)
        }
    }

    private val trendingAnimeAdapter: RecommendAnimeAdapter by lazy {
        RecommendAnimeAdapter { animeInfo ->
            navigateToDetail(animeInfo)
        }
    }

    private val genreAnimeAdapter: GenreAnimeAdapter by lazy {
        GenreAnimeAdapter { animeInfo ->
            //navigateToDetail(animeInfo)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            Timber.tag("currentUser").d(currentUser.uid)
        } else {
            Timber.tag("current User is Null")
        }

        bind {
            vm = viewModel
            recommendAdapter = recommendAnimeAdapter
            trendingAdapter = trendingAnimeAdapter
            genreAdapter = genreAnimeAdapter
        }

        repeatOnStarted {
            viewModel.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    //이러면 똑같은 화면이 데이터만 바껴서 두개가 만들어짐
    private fun navigateToDetail(kitsuAnimeInfo: KitsuAnimeInfo) {
        startActivity(
            Intent(requireActivity(), AnimeReviewActivity::class.java).apply {
                Timber.d("$kitsuAnimeInfo")
                putExtra(ANIME_INFO, kitsuAnimeInfo)
            }
        )
    }

//    private fun navigateToDetail(animeInfo: AnimeInfo) {
//        startActivity(
//            Intent(requireActivity(), AnimeReviewActivity::class.java).apply {
//                Timber.d("$animeInfo")
//                putExtra(ANIME_INFO, animeInfo)
//            }
//        )
//    }

    private fun handleEvent(event: HomeViewModel.Event) = when (event) {
        is HomeViewModel.Event.SearchIconClick -> navigateToSearch()
    }

    private fun navigateToSearch() {
        //findNavController().navigate(R.id.action_navigation_home_to_searchFragment)
        findNavController().safeNavigate(HomeFragmentDirections.actionFragmentHomeToFragmentAnimeSearch())
        //findNavController().safeNavigate(R.id.fragment_home, R.id.action_fragment_home_to_fragment_anime_search)
    }
}