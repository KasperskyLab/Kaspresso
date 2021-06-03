package com.kaspersky.kaspresso_sample_core.docloc

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class FragmentTestActivity : AppCompatActivity() {

    fun setFragment(fragment: Fragment) =
        with(supportFragmentManager.beginTransaction()) {
            replace(android.R.id.content, fragment)
            commit()
        }
}
