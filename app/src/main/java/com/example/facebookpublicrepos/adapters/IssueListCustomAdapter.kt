package com.example.facebookpublicrepos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.facebookpublicrepos.R
import com.example.facebookpublicrepos.models.Issue

/**
 * This is the issue recycler view custom adapter
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class IssueListCustomAdapter : RecyclerView.Adapter<IssueListCustomAdapter.ViewHolder>() {

    //region Local instances
    private lateinit var context: Context
    private val issueList: ArrayList<Issue> = ArrayList()
    //endregion

    //region on create view holder
    /**
     * @inheritdoc
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
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
        return issueList.size
    }
    //endregion

    //region On bind view holder
    /**
     * @inheritdoc
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val issueFound: Issue = issueList[position]
        holder.bind(issueFound)
    }
    //endregion

    //region Add data
    /**
     * Add data to list
     *
     * @param data The updated list
     */
    fun addData(data : List<Issue> )
    {
        for (issueFound in data) {

            if (!issueList.contains(issueFound)) {
                this.issueList.add(issueFound)
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
        private val issue_title = itemView.findViewById<TextView>(R.id.description)
        private val issue_description = itemView.findViewById<TextView>(R.id.details)
        private val user_icon = itemView.findViewById<ImageView>(R.id.icon)

        fun bind(issueFound: Issue) {

            issue_title.text = String.format("%s \nby %s",issueFound.title,issueFound.user.name)
            issue_description.text = issueFound.description

            val placeholder_image = AppCompatResources.getDrawable(context, R.drawable.repo_issue_icon)

            Glide
                .with(context)
                .load(issueFound.user.avatar_url)
                .centerCrop().placeholder(placeholder_image)
                .into(user_icon)
        }
    }
    //endregion
}