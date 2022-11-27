package com.example.mdpassignment6

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mdpassignment6.data.Education
import kotlinx.android.synthetic.main.component_education_brief.view.*

class MyEducationAdapter(var context: Context, val educationList: ArrayList<Education>
): RecyclerView.Adapter<MyEducationAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyEducationAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_education_brief,parent,false);
        return MyViewHolder(view);
    }

    override fun onBindViewHolder(holder: MyEducationAdapter.MyViewHolder, position: Int) {
//        holder.itemView.ceb_logo.setBackgroundResource(educationList[position].logo);
        holder.itemView.ceb_name.text = educationList[position].name;
        holder.itemView.ceb_degree.text = educationList[position].degree;
    }

    override fun getItemCount(): Int {
        return educationList.size;
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView);
}