package com.example.mdpassignment6

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mdpassignment6.data.Contact
import kotlinx.android.synthetic.main.component_contact.view.*
import com.example.mdpassignment6.MainActivity


class MyContactAdapter(var context: Context, val contactList: ArrayList<Contact>
): RecyclerView.Adapter<MyContactAdapter.MyViewHolder>() {

    private var position = 0;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyContactAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_contact,parent,false);
        return MyViewHolder(view);
    }

    override fun onBindViewHolder(holder: MyContactAdapter.MyViewHolder, position: Int) {
//        holder.itemView.cct_icon.setBackgroundResource(contactList[position].icon);

        holder.itemView.cct_title.text = contactList[position].title;
        holder.itemView.cct_type.text = contactList[position].type;

        when(holder.itemView.cct_type.text) {
            "Mobile" ->
                holder.itemView.cct_icon.setBackgroundResource(R.drawable.ic_baseline_phone_24)
            "Email" ->
                holder.itemView.cct_icon.setBackgroundResource(R.drawable.ic_baseline_email_24)
            "Linkedin" ->
                holder.itemView.cct_icon.setBackgroundResource(R.drawable.linkedin)
            "Github" ->
                holder.itemView.cct_icon.setBackgroundResource(R.drawable.github)
            "Resume Pdf" ->
                holder.itemView.cct_icon.setBackgroundResource(R.drawable.ic_baseline_picture_as_pdf_24)
        }

        holder.itemView.setOnClickListener{
            Toast.makeText(
                context,
                "You clicked " + holder.itemView.cct_title.text,
                Toast.LENGTH_SHORT
            ).show();
        };

        holder.itemView.setOnLongClickListener{
            when(holder.itemView.cct_type.text) {
                "Mobile" ->
                    onLongClickMobile(holder, position);
                "Email" ->
                    onLongClickEmail(holder, position);
                "Linkedin" ->
                    onLongClickLink(holder, position);
                "Github" ->
                    onLongClickLink(holder, position);
                "Resume Pdf" ->
                    Toast.makeText(
                        context,
                        "You chose pdf",
                        Toast.LENGTH_SHORT
                    ).show();
                else ->
                    Toast.makeText(
                        context,
                        "You chose other",
                        Toast.LENGTH_SHORT
                    ).show();
            };
            true;
        };
    }

    fun onLongClickMobile(holder: MyContactAdapter.MyViewHolder, position: Int): Boolean{
        val uri = Uri.parse("tel:"+holder.itemView.cct_title.text.toString());
        val  it = Intent(Intent.ACTION_DIAL, uri);
        context.startActivity(it);
        return true;
    }

    fun onLongClickLink(holder: MyContactAdapter.MyViewHolder, position: Int): Boolean{
        val uri = Uri.parse(holder.itemView.cct_title.text.toString());
        val  it = Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(it);
        return true;
    }
    fun onLongClickEmail(holder: MyContactAdapter.MyViewHolder, position: Int): Boolean{
        val email = holder.itemView.cct_title.text.toString();
        val uri = Uri.parse("mailto:"+email);
        val it = Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra(Intent.EXTRA_EMAIL, email);
        it.putExtra(Intent.EXTRA_SUBJECT, "From CV App");
        it.putExtra(Intent.EXTRA_TEXT, "This is test message from CV App");
        context.startActivity(it);
        return true;
    }

//    fun onLongClickMobile(holder: MyContactAdapter.MyViewHolder, position: Int): Boolean{
//        var flag: Boolean = false;
//        val popupMenu = PopupMenu(context, holder.itemView);
//        popupMenu.inflate(R.menu.menu_popup_phone);
//        popupMenu.setOnMenuItemClickListener { menuItem ->
//            when (menuItem.itemId){
//                R.id.menu_call -> {
//                    val uri = Uri.parse("tel:"+holder.itemView.cct_title.text.toString());
//                    val  it = Intent(Intent.ACTION_DIAL, uri);
//                    context.startActivity(it);
//                    flag = true;
//                    flag;
//                }
//                R.id.menu_sms -> {
//                    val uri = Uri.parse("tel:"+holder.itemView.cct_title.text.toString());
//                    val  it = Intent(Intent.ACTION_SENDTO, uri);
//                    context.startActivity(it);
//                    flag = true;
//                    flag;
//                }
//                else -> false;
//            };
//        };
//        popupMenu.show();
//        return flag;
//    }

    override fun getItemCount(): Int {
        return contactList.size;
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView);
}