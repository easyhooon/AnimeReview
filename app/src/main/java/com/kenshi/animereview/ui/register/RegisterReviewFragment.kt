package com.kenshi.animereview.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.databinding.FragmentRegisterReviewBinding
import com.kenshi.animereview.ui.MainViewModel
import com.kenshi.animereview.ui.base.BaseFragment
import com.kenshi.animereview.ui.common.EventObserver
import com.kenshi.animereview.ui.common.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterReviewFragment : BaseFragment<FragmentRegisterReviewBinding>(R.layout.fragment_register_review) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind {
            anime = viewModel.animeInfo.value
            vm = viewModel
        }
        initNavigation()
        initRatingBar()
    }

    private fun initNavigation() {
        binding.tbRegisterReview.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.returnHomeEvent.observe(viewLifecycleOwner, EventObserver {
            returnHome()
        })
    }

    private fun returnHome() {
        showToast("리뷰가 등록되었습니다")
        //리뷰 editText 값 초기화
        viewModel.resetReviewText()
        findNavController().navigate(R.id.action_navigation_register_review_to_navigation_home)
    }

    private fun initRatingBar() {
        binding.rbRegisterReview.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.setRating(rating)
        }
    }
}
