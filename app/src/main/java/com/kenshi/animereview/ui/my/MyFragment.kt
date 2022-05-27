package com.kenshi.animereview.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.User
import com.kenshi.animereview.databinding.FragmentMyBinding
import com.kenshi.animereview.ui.base.BaseFragment
import com.kenshi.animereview.ui.base.UiState
import com.kenshi.animereview.ui.base.successOrNull
import com.kenshi.animereview.ui.common.collectLatestLifecycleFlow
import com.kenshi.animereview.ui.common.launchAndRepeatWithViewLifecycle
import com.kenshi.animereview.ui.home.RecommendAnimeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

//TODO
// 내가 쓴 리뷰를 보여주는 화면
// recyclerview 의 header 에 내가 작성한 총 리뷰의 개수를 띄워줌
// review 의 상세 페이지로 이동

@AndroidEntryPoint
class MyFragment : BaseFragment<FragmentMyBinding>(R.layout.fragment_my) {

    private val viewModel: MyViewModel by viewModels()

//    private val reviewAdapter: ReviewAdapter by lazy {
//        ReviewAdapter { review ->
//            navigateToDetail(review)
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            //user = viewModel.user.value
            //user = viewModel.userInfo.value.successOrNull()
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
        collectLatestLifecycleFlow(viewModel.userInfo) { userInfo ->
            binding.user = userInfo.successOrNull()
        }
    }
}