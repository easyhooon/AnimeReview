package com.kenshi.animereview.ui.review

import android.os.Bundle
import android.util.Log
import com.kenshi.animereview.R
import com.kenshi.animereview.databinding.FragmentReviewBinding
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment: BaseFragment<FragmentReviewBinding>(R.layout.fragment_review) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate", "ReviewFragment:${this.hashCode()}")
    }
}