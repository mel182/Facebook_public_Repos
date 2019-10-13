package com.example.facebookpublicrepos.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.facebookpublicrepos.R
import com.example.facebookpublicrepos.repositories.RepositoriesListRepository
import com.example.facebookpublicrepos.viewmodels.RepositoriesViewModel

class MainActivity : AppCompatActivity() {

    private var repositoriesViewModel: RepositoriesViewModel? = null

    // ------------ Variables for pagination ----------
    private var isLoading = true
    private val DEFAULT_COUNT : Int = 0
    private var pastVisibleItems: Int = DEFAULT_COUNT
    private var visibleItemCount: Int = DEFAULT_COUNT
    private var totalItemCount: Int = DEFAULT_COUNT
    private var previous_total: Int = DEFAULT_COUNT
    private var threshold = 10
    // -------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repositoriesViewModel = ViewModelProviders.of(this).get(RepositoriesViewModel::class.java)

        repositoriesViewModel!!.getRepositoryList(1).observe(
            this,
            Observer {
                Log.i("list","List: $it")
            }
        )
    }
}
