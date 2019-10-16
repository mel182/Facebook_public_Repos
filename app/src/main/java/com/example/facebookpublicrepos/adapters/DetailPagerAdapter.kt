package com.example.facebookpublicrepos.adapters

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.facebookpublicrepos.R
import com.example.facebookpublicrepos.fragments.ContributorsFragment
import com.example.facebookpublicrepos.fragments.IssuesFragment
import com.example.facebookpublicrepos.models.FragmentItem

class DetailPagerAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{

    private val fragments = arrayOf(
        FragmentItem(context.resources.getString(R.string.contributors),ContributorsFragment()),
        FragmentItem(context.resources.getString(R.string.issues),IssuesFragment())
    )

    override fun getItem(position: Int): Fragment
    {
        return fragments[position].fragment
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence?
    {
        return fragments[position].title
    }



}