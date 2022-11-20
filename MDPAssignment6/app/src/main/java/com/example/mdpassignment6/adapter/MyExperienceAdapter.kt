package com.example.mdpassignment6

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mdpassignment6.data.Experience
import kotlinx.android.synthetic.main.component_work_brief.view.*

class MyExperienceAdapter(var context: Context, val experienceList: ArrayList<Experience>
): RecyclerView.Adapter<MyExperienceAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyExperienceAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_work_brief,parent,false);
        return MyViewHolder(view);
    }

    override fun onBindViewHolder(holder: MyExperienceAdapter.MyViewHolder, position: Int) {
        holder.itemView.cwb_logo.setBackgroundResource(experienceList[position].logo);
        holder.itemView.cwb_company.text = experienceList[position].company;
        holder.itemView.cwb_position.text = experienceList[position].position;
        holder.itemView.cwb_period.text = experienceList[position].period;
        holder.itemView.cwb_location.text = experienceList[position].location;
        holder.itemView.cwb_shortDesc.text = experienceList[position].shortDesc;
    }

    override fun getItemCount(): Int {
        return experienceList.size;
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView);
}