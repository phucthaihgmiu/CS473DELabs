package com.example.problem2


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.problem2.data.Product
import kotlinx.android.synthetic.main.brief_item.view.*

class CategoryAdapter(var context: Context, val products: ArrayList<Product>
): RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.brief_item,parent,false);
        return MyViewHolder(view);
    }

    override fun onBindViewHolder(holder: CategoryAdapter.MyViewHolder, position: Int) {
        holder.itemView.bi_ivPhoto.setBackgroundResource(products[position].image);
        holder.itemView.bi_tvTitle.text = products[position].title;
        holder.itemView.bi_tvPrice.text = products[position].price.toString();
        holder.itemView.bi_tvColor.text = products[position].color;

        holder.itemView.setOnClickListener{
            val intent = Intent(context, ProductActivity::class.java);
            val product = products[position];
            intent.putExtra("product", product);
            context.startActivity(intent);
        };
    }

    override fun getItemCount(): Int {
        return products.size;
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView);
}