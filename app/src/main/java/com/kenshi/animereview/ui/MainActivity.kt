package com.kenshi.animereview.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.databinding.ActivityMainBinding
import com.kenshi.animereview.ui.base.BaseActivity
import com.kenshi.animereview.ui.common.gone
import com.kenshi.animereview.ui.common.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home, R.id.navigation_my -> {
                    binding.bottomNav.visible()
                    //binding.tbAnime.gone()
                }
                else -> {
                    binding.bottomNav.gone()
                    //binding.tbAnime.visible()
                }
            }
        }

        binding.bottomNav.setupWithNavController(navController)

//        binding.tbAnime.apply {
//            setSupportActionBar(this)
//            setNavigationOnClickListener {
//                if (!navController.navigateUp()) finish() else navController.navigateUp()
//            }
//        }
    }
}