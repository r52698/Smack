package com.example.smack.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smack.Model.Message
import com.example.smack.R
import com.example.smack.Services.UserDataService

class MessageAdapter(val context : Context, val messages: ArrayList<Message>) : RecyclerView.Adapter<MessageAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.Holder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.message_list_view, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return messages.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bindMessage(messages[position], context)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImage = itemView?.findViewById<ImageView>(R.id.messageUserImage)
        val userName = itemView?.findViewById<TextView>(R.id.messageUserNameLbl)
        val timeStamp = itemView?.findViewById<TextView>(R.id.timeStampLbl)
        val messageBody = itemView?.findViewById<TextView>(R.id.messageBodyLbl)

        fun bindMessage(message: Message, context: Context) {
            val resourceId = context.resources.getIdentifier(message.userAvatar,
                "drawable", context.packageName)
            avatarImage.setImageResource(resourceId)
            avatarImage.setBackgroundColor(UserDataService.returnAvatarColor(message.userAvatarColor))
            userName.text = message.userName
            timeStamp.text = message.timeStamp
            messageBody.text = message.message
        }
    }
}