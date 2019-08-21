package com.kaspersky.kaspressample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class HomeFragment : Fragment() {
    companion object {
        fun newInstance() = HomeFragment()

        const val TAG = "HomeFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}