package com.kenshi.animereview.ui.review

import android.os.Bundle
import android.util.Log
import android.view.View
import com.kenshi.animereview.R
import com.kenshi.animereview.databinding.FragmentReviewBinding
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

//TODO
// 내가 쓴 리뷰를 보여주는 화면
// recyclerview 의 header 에 내가 작성한 총 리뷰의 개수를 띄워줌
// review 의 상세 페이지로 이동



@AndroidEntryPoint
class ReviewFragment: BaseFragment<FragmentReviewBinding>(R.layout.fragment_review) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("onCreate").d("ReviewFragment")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.tag("onViewCreated").d("ReviewFragment:%s", this.hashCode())
    }

}