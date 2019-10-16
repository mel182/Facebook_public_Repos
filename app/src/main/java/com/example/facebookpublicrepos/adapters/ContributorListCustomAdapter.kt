package com.example.facebookpublicrepos.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.facebookpublicrepos.R
import com.example.facebookpublicrepos.activity.DetailActivity
import com.example.facebookpublicrepos.constants.Constant
import com.example.facebookpublicrepos.models.Contributor
import com.example.facebookpublicrepos.models.Repository

/**
 * This is the contributors recycler view custom adapter
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class ContributorListCustomAdapter : RecyclerView.Adapter<ContributorListCustomAdapter.ViewHolder>(){

    //region local instances
    private lateinit var context:Context
    private val contributorList: ArrayList<Contributor> = ArrayList()
    //endregion

    //region On create view
    /**
     * @inheritdoc
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val cellView = LayoutInflater.from(context).inflate(R.layout.detail_custom_cell, parent, false)
        return ViewHolder(cellView,context)
    }
    //endregion

    //region Get item count
    /**
     * @inheritdoc
     */
    override fun getItemCount(): Int {
        return contributorList.size
    }
    //endregion

    //region On bind view holder
    /**
     * @inheritdoc
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val contributorFound: Contributor = contributorList[position]
        holder.bind(contributorFound)
    }
    //endregion

    //region Add data
    /**
     * Add data to list
     * @param data The contributors updated list
     */
    fun addData(data : List<Contributor> )
    {
        for (contributorFound in data) {

            if (!contributorList.contains(contributorFound)) {
                this.contributorList.add(contributorFound)
            }
        }

        notifyDataSetChanged()
    }
    //endregion

    //region View holder class
    /**
     * This is the view holder class
     * @author Melchior Vrolijk
     */
    class ViewHolder(itemView: View, val context:Context) : RecyclerView.ViewHolder(itemView) {
        private val contributor_name = itemView.findViewById<TextView>(R.id.description)
        private val contribution_description = itemView.findViewById<TextView>(R.id.details)
        private val contributor_icon = itemView.findViewById<ImageView>(R.id.icon)

        fun bind(contributor: Contributor) {

            contributor_name.text = contributor.name
            contribution_description.text = String.format("%d commits",contributor.contributions)

            val placeholder_image = AppCompatResources.getDrawable(context, R.drawable.repository_icon)

            Glide
                .with(context)
                .load(contributor.avatar_url)
                .centerCrop().placeholder(placeholder_image)
                .into(contributor_icon)
        }
    }
    //endregion

}