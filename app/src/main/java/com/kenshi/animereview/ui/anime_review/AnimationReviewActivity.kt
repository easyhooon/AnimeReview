package com.kenshi.animereview.ui.anime_review


import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.databinding.ActivityAnimationReviewBinding
import com.kenshi.animereview.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimationReviewActivity : BaseActivity<ActivityAnimationReviewBinding>(R.layout.activity_animation_review) {

    private val viewModel: AnimeReviewViewModel by viewModels()
    private lateinit var navController: NavController

    lateinit var animeInfo: AnimeInfo

    companion object {
        const val ANIME_INFO = "animeInfo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.apply {
            animeInfo = getParcelableExtra(ANIME_INFO)!!
        }
        viewModel.setAnimeInfo(animeInfo)

        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
    }
}