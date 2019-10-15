package com.example.facebookpublicrepos.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.facebookpublicrepos.R
import com.example.facebookpublicrepos.adapters.DetailPageAdapter
import com.example.facebookpublicrepos.constants.Constant
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val fragmentAdapter = DetailPageAdapter(supportFragmentManager,this)
        viewpager_main.adapter = fragmentAdapter
        viewpager_main.setSwipePagingEnabled(false)

        val tabLayout = findViewById<TabLayout>(R.id.tabs_main)
        tabLayout.setupWithViewPager(viewpager_main)

        val selected_repo = intent.getStringExtra(Constant.SELECTED_REPO)
        title = selected_repo
    }
}
