package com.example.mdpassignment6

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mdpassignment6.data.Contact
import kotlinx.android.synthetic.main.component_contact.view.*

class MyContactAdapter(var context: Context, val contactList: ArrayList<Contact>
): RecyclerView.Adapter<MyContactAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyContactAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_contact,parent,false);
        return MyViewHolder(view);
    }

    override fun onBindViewHolder(holder: MyContactAdapter.MyViewHolder, position: Int) {
        holder.itemView.cct_icon.setBackgroundResource(contactList[position].icon);
        holder.itemView.cct_title.text = contactList[position].title;
        holder.itemView.cct_type.text = contactList[position].type;
    }

    override fun getItemCount(): Int {
        return contactList.size;
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView);
}