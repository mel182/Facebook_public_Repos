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

/**
 * This is the detail section pager adapter
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class DetailPagerAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{

    //region Fragment arrays
    /**
     * List containing corresponding fragments
     */
    private val fragments = arrayOf(
        FragmentItem(context.resources.getString(R.string.contributors),ContributorsFragment()),
        FragmentItem(context.resources.getString(R.string.issues),IssuesFragment())
    )
    //endregion

    //region Get item
    /**
     * @inheritdoc
     */
    override fun getItem(position: Int): Fragment
    {
        return fragments[position].fragment
    }
    //endregion

    //region get count
    /**
     * @inheritdoc
     */
    override fun getCount(): Int {
        return fragments.size
    }
    //endregion

    //region get page title
    /**
     * @inheritdoc
     */
    override fun getPageTitle(position: Int): CharSequence?
    {
        return fragments[position].title
    }
    //endregion

}