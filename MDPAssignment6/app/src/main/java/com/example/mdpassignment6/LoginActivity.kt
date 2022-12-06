package com.example.mdpassignment6

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mdpassignment6.adapter.MySkillAdapter
import com.example.mdpassignment6.data.Account
import com.example.mdpassignment6.data.Skill
import com.example.mdpassignment6.util.APIService
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    var accounts:List<Account> = emptyList();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        parseAccountJSON();
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

                Toast.makeText(this, "Found user", Toast.LENGTH_LONG).show();

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

    private fun parseAccountJSON(){
        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)
        service.getAccounts().enqueue(object : Callback<List<Account>> {
            override fun onResponse(
                call: Call<List<Account>>,
                response: Response<List<Account>>
            ) {
//                val gson = GsonBuilder().setPrettyPrinting().create()
//                val prettyJson = gson.toJson(response.body())
//                Log.d("Pretty Printed JSON :", prettyJson)
                 accounts = response.body()!!;

            }

            override fun onFailure(call: Call<List<Account>>, t: Throwable) {
                Log.e("RETROFIT_ERROR", "FAILURE TO LOAD JSON")
            }

        });
    }
}