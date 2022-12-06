package com.example.problem2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.problem2.data.Category
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    var categories = arrayOf<Category>(
        Category("Mac", "src1"),
        Category("Airpods", "src2"),
        Category("iPad", "src3"),
        Category("iPhone", "src4"),
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping);
        val temp = intent.getSerializableExtra("user");
        val newUser:User = temp as User;

        val number = 1;
        val text: String = when(number){
            1-> "one"
            2-> "two"
            3-> "three"
            else -> "others"
        }

        val ob: Any = "hello"


        val user: User = intent.getSerializableExtra("user") as User;
        tvWelcome.text = "Welcome ${user?.username}";
    }

    fun clickMac(view: View){
        Toast.makeText(applicationContext,"You have chosen the Mac category of shopping", Toast.LENGTH_LONG).show()
    }

    fun clickAirpods(view: View){
        Toast.makeText(applicationContext,"You have chosen the Airpods category of shopping", Toast.LENGTH_LONG).show()
    }

    fun clickiPad(view: View){
        Toast.makeText(applicationContext,"You have chosen the iPad category of shopping", Toast.LENGTH_LONG).show()
    }

    fun clickiPhone(view: View){
        var category = Category("iPhone", "src4");
        var intent = Intent(this, CategoryActivity::class.java);
        intent.putExtra("category", category);
        startActivity(intent);
        Toast.makeText(applicationContext,"You have chosen the iPhone category of shopping", Toast.LENGTH_LONG).show()
    }
}