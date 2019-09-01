package com.kaspersky.kaspressample.docloc

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

class FragmentTestActivity : AppCompatActivity() {

    fun setFragment(fragment: Fragment) = with(supportFragmentManager.beginTransaction()) {
        replace(android.R.id.content, fragment)
        commit()
    }
}