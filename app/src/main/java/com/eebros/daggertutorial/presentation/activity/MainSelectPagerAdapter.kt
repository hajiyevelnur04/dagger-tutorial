package com.eebros.daggertutorial.presentation.activity

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.eebros.daggertutorial.R
import com.eebros.daggertutorial.ui.home.HomeFragment
import com.eebros.daggertutorial.presentation.fragment.TestFragment

private val TAB_TITLES = arrayOf(
    R.string.first_tab,
    R.string.second_tab
)

private val TAB_FRAGMENTS = arrayOf(
    HomeFragment(),
    TestFragment()
)

class MainSelectPagerAdapter(private val context: Context, supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return TAB_FRAGMENTS[position]
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

}
