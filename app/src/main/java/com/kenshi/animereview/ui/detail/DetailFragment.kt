package com.kenshi.animereview.ui.detail

import android.os.Bundle
import android.util.Log
import com.kenshi.animereview.R
import com.kenshi.animereview.databinding.FragmentDetailBinding
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate", "DetailFragment:${this.hashCode()}")
    }
}