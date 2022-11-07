package com.example.problem2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shopping.*

class Shopping_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping);
        val user: User? = intent.getSerializableExtra("user") as User?;
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
        Toast.makeText(applicationContext,"You have chosen the iPhone category of shopping", Toast.LENGTH_LONG).show()
    }
}