package com.kenshi.animereview.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.databinding.ActivityMainBinding
import com.kenshi.animereview.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavigation()
    }

    private fun initNavigation() = with(binding) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.findNavController().apply {
            bottomNav.setupWithNavController(this)
            addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.fragment_anime_search) {
                    bottomNav.visibility = View.GONE
                } else {
                    bottomNav.visibility = View.VISIBLE
                }
            }
        }
    }
}