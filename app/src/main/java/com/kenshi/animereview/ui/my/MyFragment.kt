package com.kenshi.animereview.ui.my

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kenshi.animereview.R
import com.kenshi.animereview.databinding.FragmentMyBinding
import com.kenshi.animereview.ui.base.BaseFragment
import com.kenshi.animereview.ui.base.successOrNull
import com.kenshi.animereview.ui.common.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

//TODO
// 내가 쓴 리뷰를 보여주는 화면
// 1. recyclerview 의 header 에 내가 작성한 총 리뷰의 개수를 띄워줌
// 2. review의 개수가 나오도록
// 3. review 의 상세 페이지로 이동
// optional. review가 많아지면 load 시간이 길어지므로 paging 적용 (review 개수와 충돌되는 부분)


@AndroidEntryPoint
class MyFragment : BaseFragment<FragmentMyBinding>(R.layout.fragment_my) {

    private val viewModel: MyViewModel by viewModels()

    private val myReviewAdapter: MyReviewAdapter by lazy { MyReviewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 그냥 bind 하면 api 호출 자체를 안함
        bind {
            //user = viewModel.user.value
            //user = viewModel.userInfo.value.successOrNull()
            reviewAdapter = myReviewAdapter
            vm = viewModel
        }
        initObserver()
    }

    private fun initObserver() {
//        launchAndRepeatWithViewLifecycle {
//                viewModel.userInfo.collectLatest { userInfo ->
//                    binding.user = userInfo.successOrNull()
//            }
//        }
        //api 호출했는데 마지막 값이 null
        collectLatestLifecycleFlow(viewModel.userInfo) { userInfo ->
            Timber.d("${userInfo.successOrNull()}")
            binding.user = userInfo.successOrNull()
        }
    }
}