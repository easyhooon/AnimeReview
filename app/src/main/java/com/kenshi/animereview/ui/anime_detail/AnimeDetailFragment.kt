package com.kenshi.animereview.ui.anime_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.databinding.FragmentAnimeDetailBinding
import com.kenshi.animereview.ui.MainViewModel
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.kenshi.animereview.ui.common.EventObserver

//TODO Chip BindingAdapter에 style 통일해서 반복되는 설정 줄이기
//TODO review 를 가져오는 로직 자체가 잘못되었음 - 등록 후 다른 anime 들어가도 방금 리뷰를 등록한 anime 의 리뷰가 붙음

@AndroidEntryPoint
class AnimeDetailFragment: BaseFragment<FragmentAnimeDetailBinding>(R.layout.fragment_anime_detail) {

    lateinit var animeInfo: AnimeInfo

    companion object {
        const val ANIME_INFO = "animeInfo"
    }

    private val viewModel: MainViewModel by activityViewModels()

    private val animeReviewAdapter: AnimeReviewAdapter by lazy { AnimeReviewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            animeInfo = getParcelable(ANIME_INFO)!!
        }
        viewModel.setAnimeInfo(animeInfo)

        bind {
            anime = animeInfo
            reviewAdapter = animeReviewAdapter
            vm = viewModel
        }

        initNavigation()
        initRatingBar()
    }


    private fun initNavigation() {
        binding.tbAnimeDetail.setOnClickListener {
            // TODO 뒤로 가기 Shoppi 참고 구현
            findNavController().navigateUp()
        }

        viewModel.openRegisterReviewEvent.observe(viewLifecycleOwner, EventObserver {
            openRegisterReview()
        })
    }

    private fun openRegisterReview() {
        findNavController().navigate(R.id.action_animeDetailFragment_to_registerReviewFragment)
    }

    private fun initRatingBar() {
        binding.rbAnimeDetail.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.setRating(rating)
        }
    }
}