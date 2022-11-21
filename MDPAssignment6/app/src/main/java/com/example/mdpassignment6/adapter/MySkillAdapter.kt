package com.example.mdpassignment6.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mdpassignment6.R
import com.example.mdpassignment6.data.Skill
import kotlinx.android.synthetic.main.component_skill.view.*

class MySkillAdapter(var context: Context, val skillList: ArrayList<Skill>
): RecyclerView.Adapter<MySkillAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySkillAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_skill,parent,false);
        return MyViewHolder(view);
    }

    override fun onBindViewHolder(holder: MySkillAdapter.MyViewHolder, position: Int) {
        holder.itemView.csk_title.text = skillList[position].title + " : ";
        holder.itemView.csk_details.text = skillList[position].details.joinToString(", ");
    }

    override fun getItemCount(): Int {
        return skillList.size;
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView);
}