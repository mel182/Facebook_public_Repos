package com.example.facebookpublicrepos.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.facebookpublicrepos.R
import com.example.facebookpublicrepos.adapters.ContributorListCustomAdapter
import com.example.facebookpublicrepos.fragments.baseFragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.loading_layout.*

/**
 * This is the contributor [Fragment] class.
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class ContributorsFragment : BaseFragment() {

    //region Local instances
    private lateinit var customAdapter : ContributorListCustomAdapter
    //endregion

    //region On create view
    /**
     * @inheritdoc
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
    //endregion

    //region On activity created
    /**
     * @inheritdoc
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeAndSetComponents()
        performPagination(page_number)
    }
    //endregion

    //region Initialize and set components
    /**
     * Initialize and set components
     */
    private fun initializeAndSetComponents() {

        initializeBaseComponents(detail_list_recyclerview)
        customAdapter = ContributorListCustomAdapter()
        detail_list_recyclerview.adapter = customAdapter
    }
    //endregion

    //region Perform pagination task
    /**
     * @inheritdoc
     */
    override fun performPagination(page_nr: Int) {

        loading_view.visibility = View.VISIBLE

        repositoriesViewModel.getRepositoryContributors(selected_repo,page_nr).observe(
            this,
            Observer {

                if (!it.isEmpty()) {

                    page_number++
                    customAdapter.addData(it)
                }

                loading_view.visibility = View.GONE
                isLoading = false
            }
        )
    }
    //endregion
}
