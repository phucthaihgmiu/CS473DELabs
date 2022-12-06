package com.example.problem2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.problem2.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var users = mutableListOf(
        User("hong", "thai", "hongthai@email.com", "123"),
        User("tan", "phat", "tanphat@email.com", "123"),
        User("tan", "mai", "tanmai@email.com", "123")
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickSignIn(view: View) {
        var username = etEmail.text.toString().trim();
        var password = etPwd.text.toString().trim();

        if (username.isNullOrEmpty() || username.isNullOrBlank()) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
        }

        if (password.isNullOrEmpty() || password.isNullOrBlank()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
        }

        var foundUser = false;
        for (user in users) {
            if (user.username == username) {
                if (user.password == password) {
                    foundUser = true;

                    Toast.makeText(this, "Moving to next activity", Toast.LENGTH_LONG).show();
                    //move to next activity
                    var intent = Intent(this, ShoppingActivity::class.java);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Invalid password", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }

        if (!foundUser) {
            Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show();
        }


    }

    fun calc(l: Double, h: Double): Double = l * h;

    fun clickSignup(view: View){
        startActivity(Intent(this, RegisterActivity::class.java));
    }

    fun clickForgotPassword(view: View){
        Toast.makeText(applicationContext,"clicked forgot password", Toast.LENGTH_LONG).show();
        val email = etEmail.text.toString().trim();
        val intent = Intent(Intent.ACTION_SENDTO);
        intent.data = Uri.parse("mailto:");
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password");
        intent.putExtra(Intent.EXTRA_TEXT, "You clicked forgot password");
//        if(intent.resolveActivity(packageManager) != null){
            Toast.makeText(applicationContext,"email sent", Toast.LENGTH_LONG).show();
            startActivity(intent);

//        }
    }
}