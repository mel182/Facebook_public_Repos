package com.example.facebookpublicrepos.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookpublicrepos.R
import com.example.facebookpublicrepos.activity.DetailActivity
import com.example.facebookpublicrepos.constants.Constant
import com.example.facebookpublicrepos.models.Contributor
import com.example.facebookpublicrepos.models.Repository

class ContributorListCustomAdapter(val contributorList: ArrayList<Contributor>) : RecyclerView.Adapter<ContributorListCustomAdapter.ViewHolder>(){

//    private var contributorList: ArrayList<Contributor> = ArrayList()
    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val cellView = LayoutInflater.from(context).inflate(R.layout.detail_custom_cell, parent, false)
        return ViewHolder(cellView)

    }

    override fun getItemCount(): Int {
        return contributorList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val contributorFound: Contributor = contributorList[position]
        holder.bind(contributorFound)
    }



    //region View holder class
    /**
     * This is the view holder class
     * @author Melchior Vrolijk
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contributor_name = itemView.findViewById<TextView>(R.id.description)
        private val contribution_description = itemView.findViewById<TextView>(R.id.details)
        private val contributor_icon = itemView.findViewById<ImageView>(R.id.icon)
//        private val main_layout = itemView.findViewById(R.id.main_layout) as LinearLayout
//        private var context = target_context

        fun bind(contributor: Contributor) {

            contributor_name.text = contributor.name
            contribution_description.text = contributor.contributions.toString()

//            main_layout.setOnClickListener {
//
//                val detailActivityIntent = Intent(context, DetailActivity::class.java)
//                detailActivityIntent.putExtra(Constant.SELECTED_REPO,repositoryFound.name)
//                context.startActivity(detailActivityIntent)
//            }
        }
    }
    //endregion

}