package com.example.mdpassignment6

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.mdpassignment6.data.*
import com.example.mdpassignment6.util.RestApi
import com.example.mdpassignment6.util.RestApiService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    var accounts:List<Account> = emptyList();

    private var username = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getAccounts();
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
        for (account in accounts) {
            if (account.email.equals(username) && account.password.equals(password)) {
                foundUser = true;

                val spf = getSharedPreferences("myspf", Context.MODE_PRIVATE);
                val spe = spf.edit();
                spe.putString("username", account.email);
                spe.apply();

                //move to next activity
                var intent = Intent(this, MainActivity::class.java);
//              intent.putExtra("account", account);
                startActivity(intent);

                break;
            } else {
                Toast.makeText(this, "Invalid username / password", Toast.LENGTH_LONG).show();
            }
        }

        if (!foundUser) {
            Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show();
        }


    }

    private fun defaultLastUser() {

        val spf = applicationContext?.getSharedPreferences("myspf", Context.MODE_PRIVATE);
        username = spf?.getString("username", "")!!;

        if(!username.isNullOrBlank() && accounts != null && accounts.size > 0) {
            var foundUser = false;
            for (account in accounts) {
                if (account.email.equals(username)) {
                    etEmail.setText(account.email);
                    etPwd.setText(account.password);
                    break;
                }
            }
        }
    }

    private fun getAccounts(){
        val apiService = RestApiService()
        apiService.getAccounts (){
            if(it != null){
                accounts = it.filter { account ->  account.isActive};
                defaultLastUser();
            }else{
                Log.e("RETROFIT_ERROR", "FAILURE TO GET ACCOUNT")
            }
        }
    }
}