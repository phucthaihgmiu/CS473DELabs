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
//    var accounts:List<Account> = emptyList();


    private var accounts:List<Account> = ArrayList<Account>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        showLastUser();

        getAccounts();
    }

    fun onLogin(view: View) {
        var username = etEmail.text.toString().trim();
        var password = etPwd.text.toString().trim();

        if (username.isNullOrEmpty() || username.isNullOrBlank()) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
            return
        }

        if (password.isNullOrEmpty() || password.isNullOrBlank()) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show();
            return
        }

        if(!validateCredentials(username, password)){
            Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show();
        }else{
            storeLastUser()
            openMainActivity()
        }

    }

    private fun validateCredentials(username:String, password:String):Boolean{
        for (a in accounts) {
            if (a.email.equals(username) && a.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    private fun getAccounts(){
        val apiService = RestApiService()
        apiService.getAccounts (){
            if(it != null){
                accounts = it.filter { account ->  account.isActive};
            }else{
                Log.e("RETROFIT_ERROR", "FAILURE TO GET ACCOUNT")
            }
        }
    }

    private fun showLastUser() {

        val prefUser = applicationContext?.getSharedPreferences("myspf", Context.MODE_PRIVATE);
        val username = prefUser?.getString("username", "")!!;
        val password = prefUser?.getString("password", "")!!;

        etEmail.setText(username);
        etPwd.setText(password);

    }

    private fun storeLastUser(){
        val spf = getSharedPreferences("myspf", Context.MODE_PRIVATE);
        val spe = spf.edit();
        spe.putString("username", etEmail.text.toString());
        spe.putString("password", etPwd.text.toString());
        spe.apply();
    }

    private fun openMainActivity(){
        var intent = Intent(this, MainActivity::class.java);
        startActivity(intent);
    }
}