package com.example.prabh.stackelab.mvvm.activity.recentThreadsActivity

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prabh.stackelab.R
import com.example.prabh.stackelab.mvvm.activity.repliesactivity.RepliesActivity
import com.example.prabh.stackelab.retrofit.model.Thread
import kotlinx.android.synthetic.main.recent_thread_recycler.view.*

class RecentsThreadsAdapter(val itemsList: ArrayList<Thread>, val recentThreadsActivity: RecentThreadsActivity) :
    RecyclerView.Adapter<RecentsThreadsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recent_thread_recycler, parent, false))

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(itemsList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(thread: Thread) {
            itemView.date.text = thread.created_at.subSequence(0, 10)
            itemView.time.text = thread.created_at.subSequence(11, 16)
            itemView.reg_no.text = thread.register_no
            itemView.dept.text = thread.dept
            itemView.question_no.text = "Q %s".format(thread.question_no.toString())
            itemView.subject.text = thread.subject
            itemView.thread.text = thread.query
            when {
                thread.type == "guide" -> itemView.thread_type_card.setCardBackgroundColor(Color.parseColor("#bfe9aa"))
                thread.type == "query" -> itemView.thread_type_card.setCardBackgroundColor(Color.parseColor("#e9aaaa"))
                thread.type == "testcase" -> itemView.thread_type_card.setCardBackgroundColor(Color.parseColor("#b4d5e7"))
            }
            itemView.thread_type_card.setOnClickListener {
                val i = Intent(recentThreadsActivity, RepliesActivity::class.java)
                i.putExtra("thread_id", thread.thread_id)
                recentThreadsActivity.startActivity(i)
            }
        }
    }
}