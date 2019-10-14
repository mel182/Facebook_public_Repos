package com.example.facebookpublicrepos.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookpublicrepos.R
import com.example.facebookpublicrepos.adapters.RepositoryListCustomAdapter
import com.example.facebookpublicrepos.models.Repository
import com.example.facebookpublicrepos.viewmodels.RepositoriesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_bar.*

class MainActivity : AppCompatActivity() {

    //region Local instances
    private lateinit var repositoriesViewModel: RepositoriesViewModel
    private var isLoading = false
    private var page_number: Int = 1
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var customAdapter : RepositoryListCustomAdapter
    //endregion

    //region On Create
    /**
     * @inheritdoc
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialAndSetAllComponents()
        performPagination(page_number)

//        repositoriesViewModel!!.getRepositoryContributors("fbmeshd",1).observe(this,
//            Observer {
//                Log.i("list","Contributor: $it")
//            }
//        )

//        repositoriesViewModel!!.getRepositoryIssues("codemod",1).observe(this,
//            Observer {
//                Log.i("list","Issues: $it")
//            }
//        )
    }
    //endregion

    //region Initialize components
    /**
     * Initialize all components
     */
    private fun initialAndSetAllComponents() {
        repositoriesViewModel = ViewModelProviders.of(this).get(RepositoriesViewModel::class.java)

        linearLayoutManager = LinearLayoutManager(this)
        repository_list_recyclerview.layoutManager = linearLayoutManager

        customAdapter = RepositoryListCustomAdapter()
        repository_list_recyclerview.adapter = customAdapter

        setRecyclerViewOnScroll_listener()
        setSearchView()
    }
    //endregion

    //region Set search view
    private fun setSearchView() {
        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                customAdapter.filter.filter(newText)
                return true
            }
        })
    }
    //endregion

    //region Set recycler view on scroll listener
    /**
     * Set [RecyclerView.addOnScrollListener] callback to detect scrolling position that trigger pagination tasks
     */
    private fun setRecyclerViewOnScroll_listener()
    {
        repository_list_recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (linearLayoutManager.findLastVisibleItemPosition() == linearLayoutManager.itemCount - 1) // reach the bottom of the list
                {
                    if (isLoading) {
                        return
                    } else {
                        performPagination(page_number)
                        isLoading = true
                    }
                }
            }
        })
    }
    //endregion

    //region Perform pagination
    /**
     * Perform repository list pagination through the view model
     *
     * @param page_nr The page number
     */
    private fun performPagination(page_nr: Int) {

        loading_view.visibility = View.VISIBLE
        section_title.text = getString(R.string.loading_data)

        repositoriesViewModel.getRepositoryList(page_nr).observe(
            this,
            Observer {

                if (!it.isEmpty()) {

                    page_number++
                    customAdapter.addData(it)
                }

                section_title.text = getString(R.string.repositories)
                loading_view.visibility = View.GONE
                isLoading = false
            }
        )
    }
    //endregion
}
