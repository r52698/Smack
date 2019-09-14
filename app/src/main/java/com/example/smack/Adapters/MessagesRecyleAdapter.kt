package com.example.smack.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smack.Model.Message
import com.example.smack.R

class MessagesRecyleAdapter(val context : Context, val messages: List<Message>) : RecyclerView.Adapter<MessagesRecyleAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return messages.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageBody = itemView?.findViewById<TextView>(R.id.messageTextField)

        fun bindMessage(message: Message, context: Context) {
            val resourceId = context.resources.getIdentifier(message.message,
                "drawable", context.packageName)
         //   messageBody?.setImageResource(resourceId)
         //   categoryName?.text = category.title
        }

    }
}