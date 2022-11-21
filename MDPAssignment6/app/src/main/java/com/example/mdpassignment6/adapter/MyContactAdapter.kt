package com.example.mdpassignment6

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mdpassignment6.data.Contact
import kotlinx.android.synthetic.main.component_contact.view.*

class MyContactAdapter(var context: Context, val contactList: ArrayList<Contact>
): RecyclerView.Adapter<MyContactAdapter.MyViewHolder>() {

    private var position = 0;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyContactAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_contact,parent,false);
        return MyViewHolder(view);
    }

    override fun onBindViewHolder(holder: MyContactAdapter.MyViewHolder, position: Int) {
        holder.itemView.cct_icon.setBackgroundResource(contactList[position].icon);
        holder.itemView.cct_title.text = contactList[position].title;
        holder.itemView.cct_type.text = contactList[position].type;

        holder.itemView.setOnClickListener{
            Toast.makeText(
                context,
                "You clicked " + holder.itemView.cct_title.text,
                Toast.LENGTH_SHORT
            ).show();
        };

        holder.itemView.setOnLongClickListener{
            val popupMenu = PopupMenu(context, holder.itemView);
            popupMenu.inflate(R.menu.menu_popup_phone, popupMenu.menu);
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.getItemId()) {
                    R.id.menu_call -> {
                        Toast.makeText(
                            context,
                            "You clicked call " + holder.itemView.cct_title.text,
                            Toast.LENGTH_SHORT
                        ).show();
                        true;
                    }
                    R.id.menu_sms -> {
                        Toast.makeText(
                            context,
                            "You clicked sms " + holder.itemView.cct_title.text,
                            Toast.LENGTH_SHORT
                        ).show();
                        true;
                    }
                    else -> false;
                }
            };
            popupMenu.show();
        };
    }

    override fun getItemCount(): Int {
        return contactList.size;
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView);
}