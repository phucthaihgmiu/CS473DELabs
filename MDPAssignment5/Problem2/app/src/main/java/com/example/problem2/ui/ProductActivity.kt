package com.example.problem2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.problem2.data.Product
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val product: Product? = intent.getSerializableExtra("product") as Product?;
        if(product != null && !product.title.isNullOrEmpty()){
            pa_ivPhoto.setBackgroundResource(product.image);
            pa_tvTitle.text = product.title.toString();
            pa_tvColor.text = product.color.toString();
            pa_tvId.text = product.itemid.toString();
            pa_tvDesc.text = product.desc.toString();
        }
    }
}