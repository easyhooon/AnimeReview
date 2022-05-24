package com.kenshi.animereview.ui.review_detail

import android.os.Bundle
import android.view.View
import com.kenshi.animereview.R
import com.kenshi.animereview.databinding.FragmentReviewDetailBinding
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewDetailFragment: BaseFragment<FragmentReviewDetailBinding>(R.layout.fragment_review_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {

        }
    }
}