package com.example.problem2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun clickSignup(view: View){
        if(!etFn.text.isEmpty() && !etLn.text.isEmpty() && !etEmail.text.isEmpty() && !etPwd.text.isEmpty()){
            var user: User = User(etFn.text.trim().toString(),
                etLn.text.trim().toString(),
                etEmail.text.trim().toString(),
                etPwd.text.trim().toString());

            Toast.makeText(applicationContext,"Account created successfully", Toast.LENGTH_LONG).show();
            var intent = Intent(this, MainActivity::class.java);
            intent.putExtra("user", user);
            startActivity(intent);
        }
    }
}