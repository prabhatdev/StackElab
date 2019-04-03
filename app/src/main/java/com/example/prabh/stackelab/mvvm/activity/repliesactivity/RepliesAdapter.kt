package com.example.prabh.stackelab.mvvm.activity.repliesactivity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prabh.stackelab.R
import com.example.prabh.stackelab.retrofit.model.Reply
import com.example.prabh.stackelab.utility.Session
import kotlinx.android.synthetic.main.replies_recycler.view.*


class RepliesAdapter(val itemsList: ArrayList<Reply>, val repliesActivity: RepliesActivity) :
    RecyclerView.Adapter<RepliesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.replies_recycler, parent, false))

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(itemsList[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(reply: Reply) {
            if(reply.register_no==repliesActivity.session.getStringValue(Session.REGISTER_NUMBER)){
                itemView.user_reply_card.visibility=View.VISIBLE
                itemView.reply_card.visibility=View.GONE
                itemView.user_reg_no_reply.text=reply.register_no
                itemView.user_dept_reply.text=reply.dept
                itemView.user_date_reply.text=reply.created_at.subSequence(0,10)
                itemView.user_time_reply.text=reply.created_at.subSequence(11,16)
                itemView.user_thread_reply.text=reply.reply_text
            }
            else{

                itemView.user_reply_card.visibility=View.GONE
                itemView.reply_card.visibility=View.VISIBLE
                itemView.reg_no_reply.text=reply.register_no
                itemView.dept_reply.text=reply.dept
                itemView.date_reply.text=reply.created_at.subSequence(0,10)
                itemView.time_reply.text=reply.created_at.subSequence(11,16)
                itemView.thread_reply.text=reply.reply_text
            }
        }
    }
}