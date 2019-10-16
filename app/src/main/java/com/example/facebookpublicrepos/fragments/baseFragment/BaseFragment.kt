package com.example.facebookpublicrepos.fragments.baseFragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookpublicrepos.constants.Constant
import com.example.facebookpublicrepos.viewmodels.RepositoriesViewModel

abstract class BaseFragment : Fragment()
{
    //region Local instances
    protected lateinit var repositoriesViewModel: RepositoriesViewModel
    protected lateinit var linearLayoutManager: LinearLayoutManager
    protected lateinit var selected_repo: String
    private set
    protected var isLoading = false
    protected var page_number: Int = 1
    //endregion

    //region Initialize and set base components
    /**
     * Initialize and set base components
     */
    protected fun initializeBaseComponents(recyclerView: RecyclerView) {

        repositoriesViewModel = ViewModelProviders.of(this).get(RepositoriesViewModel::class.java)
        selected_repo = this.activity!!.intent.getStringExtra(Constant.SELECTED_REPO) as String
        linearLayoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = linearLayoutManager
        setRecyclerViewOnScroll_listener(recyclerView)
    }
    //endregion

    //region Set recycler view on scroll listener
    /**
     * Set [RecyclerView.addOnScrollListener] callback to detect scrolling position that trigger pagination tasks
     */
    private fun setRecyclerViewOnScroll_listener(recyclerView: RecyclerView) {

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

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
     * @param page_nr The current paging page number
     */
    abstract fun performPagination(page_nr:Int)
    //endregion
}