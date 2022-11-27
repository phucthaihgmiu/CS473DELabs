package com.example.mdpassignment6

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mdpassignment6.data.Certification
import com.example.mdpassignment6.data.Education
import kotlinx.android.synthetic.main.component_certification_brief.view.*
import kotlinx.android.synthetic.main.component_education_brief.view.*

class MyCertificationAdapter(var context: Context, val certificationList: ArrayList<Certification>
): RecyclerView.Adapter<MyCertificationAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCertificationAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_certification_brief,parent,false);
        return MyViewHolder(view);
    }

    override fun onBindViewHolder(holder: MyCertificationAdapter.MyViewHolder, position: Int) {
//        holder.itemView.ccb_logo.setBackgroundResource(R.drawable.);
        holder.itemView.ccb_title.text = certificationList[position].title;
    }

    override fun getItemCount(): Int {
        return certificationList.size;
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView);
}