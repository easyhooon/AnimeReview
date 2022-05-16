package com.kenshi.animereview.ui.home

import android.os.Bundle
import android.util.Log
import com.kenshi.animereview.R
import com.kenshi.animereview.databinding.FragmentHomeBinding
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate", "HomeFragment:${this.hashCode()}")
    }
}