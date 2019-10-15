package com.example.facebookpublicrepos.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookpublicrepos.R
import com.example.facebookpublicrepos.activity.DetailActivity
import com.example.facebookpublicrepos.constants.Constant
import com.example.facebookpublicrepos.models.Repository

/**
 * This is the repository recycler view custom adapter
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class RepositoryListCustomAdapter() : RecyclerView.Adapter<RepositoryListCustomAdapter.ViewHolder>(), Filterable{

    //region local instances
    private var repositoryListCompleteSet: ArrayList<Repository> = ArrayList()
    private var repositoryFilterList: ArrayList<Repository> = ArrayList()
    private lateinit var context: Context
    //endregion

    //region onCreateViewHolder
    /**
     * @inheritdoc
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val cellView = LayoutInflater.from(parent.context).inflate(R.layout.repository_custom_cell, parent, false)
        return ViewHolder(cellView, context)
    }
    //endregion

    //region onBindViewHolder
    /**
     * @inheritdoc
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val repositoryFound: Repository = repositoryFilterList[position]
        holder.bind(repositoryFound)
    }
    //endregion

    //region getItemCount
    /**
     * @inheritdoc
     */
    override fun getItemCount(): Int {
        return repositoryFilterList.size
    }
    //endregion

    //region Add data
    /**
     * Add data to the list
     */
    fun addData(list: List<Repository>) {

        for (repository in list) {

            if (!repositoryListCompleteSet.contains(repository)) {
                this.repositoryListCompleteSet.add(repository)
                this.repositoryFilterList.add(repository)
            }
        }

        notifyDataSetChanged()
    }
    //endregion

    //region Get filter
    /**
     * @inheritdoc
     */
    @Suppress("UNCHECKED_CAST")
    override fun getFilter(): Filter {

        return object: Filter() {
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                repositoryFilterList.clear()
                repositoryFilterList.addAll(results!!.values as ArrayList<Repository>)
                notifyDataSetChanged()
            }

            @SuppressLint("DefaultLocale")
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val filterData: ArrayList<Repository> = ArrayList()

                val queryString = constraint?.toString()?.toLowerCase()


                if (queryString == null || queryString.isEmpty()) {

                    filterData.addAll(repositoryListCompleteSet)

                } else {

                    repositoryListCompleteSet.forEach{
                        if (it.name.toLowerCase().contains(queryString)) {

                            if (!filterData.contains(it))
                            {
                                filterData.add(it)
                            }
                        }
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filterData

                return filterResults
            }
        }
    }
    //endregion

    //region View holder class
    /**
     * This is the view holder class
     * @author Melchior Vrolijk
     */
    class ViewHolder(itemView: View, target_context: Context) : RecyclerView.ViewHolder(itemView) {
        private val repository_name = itemView.findViewById(R.id.repository_name) as TextView
        private val repository_description = itemView.findViewById(R.id.description) as TextView
        private val main_layout = itemView.findViewById(R.id.main_layout) as LinearLayout
        private var context = target_context

        fun bind(repositoryFound: Repository) {

            repository_name.text = repositoryFound.name
            repository_description.text = repositoryFound.description

            main_layout.setOnClickListener {

                val detailActivityIntent = Intent(context, DetailActivity::class.java)
                detailActivityIntent.putExtra(Constant.SELECTED_REPO,repositoryFound.name)
                context.startActivity(detailActivityIntent)
            }
        }
    }
    //endregion
}