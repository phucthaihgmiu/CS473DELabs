package com.example.problem2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.problem2.data.Category
import com.example.problem2.data.Product
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_shopping.*

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        val products = ArrayList<Product>();
        products.add(Product("iPhone14 Pro Max", 1200.00, "black", R.drawable.iphone14promax, "1", "iPhone 14 6.7inch ","iPhone"));
        products.add(Product("iPhone14 Pro", 1000.00, "red", R.drawable.iphone14pro, "2", "iPhone 14 6.1inch","iPhone"));
        products.add(Product("iPhone13", 800.00, "black", R.drawable.iphone13, "3", "iPhone 13 older version","iPhone"));

        products.add(Product("Macbook Air M1", 1200.00, "black", R.drawable.macbookairm1, "1", "MacBook Air with M1 chip","Mac"));
        products.add(Product("Macbook Air M2", 900.00, "red", R.drawable.macbookairm2, "2", "MacBook Air with M2 chip","Mac"));
        products.add(Product("Macbook Pro 13", 1300.00, "black", R.drawable.macbookpro13, "3", "MacBook Pro 13-inch","Mac"));

        rvCategory.layoutManager = LinearLayoutManager(this);

        val category: Category? = intent.getSerializableExtra("category") as Category?;
        var adapter: CategoryAdapter?;
        if(category != null && !category.title.isNullOrEmpty()){
            adapter = CategoryAdapter(this,
                products
                    .filter { product -> product.category == "iPhone" }
                    .toCollection(arrayListOf())
            );
        }else{
            adapter = CategoryAdapter(this, products);
        }
        rvCategory.adapter = adapter;
    }
}