package com.kenshi.animereview.ui.detail

import android.os.Bundle
import android.util.Log
import com.kenshi.animereview.databinding.FragmentDetailBinding
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate", "DetailFragment:${this.hashCode()}")
    }
}