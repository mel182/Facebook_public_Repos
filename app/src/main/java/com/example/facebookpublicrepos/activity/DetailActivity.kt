package com.example.facebookpublicrepos.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.facebookpublicrepos.R
import com.example.facebookpublicrepos.adapters.DetailPagerAdapter
import com.example.facebookpublicrepos.constants.Constant
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * This is the detail activity class
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class DetailActivity : AppCompatActivity() {

    //region On create
    /**
     * @inheritdoc
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setFragmentAdapter()
        setTabLayoutViewPager()
        setTitle()

    }
    //endregion

    //region Set fragment adapter
    /**
     * Set fragment adapter
     */
    private fun setFragmentAdapter() {
        val fragmentAdapter = DetailPagerAdapter(supportFragmentManager,this)
        viewpager_main.adapter = fragmentAdapter
        viewpager_main.setSwipePagingEnabled(false)
    }
    //endregion

    //region Set tab layout adapter
    /**
     * Set tab layout view pager
     */
    private fun setTabLayoutViewPager() {
        val tabLayout = findViewById<TabLayout>(R.id.tabs_main)
        tabLayout.setupWithViewPager(viewpager_main)
    }
    //endregion

    //region Set view title
    /**
     * Set title
     */
    private fun setTitle() {
        title = intent.getStringExtra(Constant.SELECTED_REPO)
    }
    //endregion

}
