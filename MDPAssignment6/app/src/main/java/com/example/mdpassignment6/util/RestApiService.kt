package com.example.mdpassignment6.util

import android.util.Log
import com.example.mdpassignment6.data.Account
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {

    fun getAccounts(onResult: (List<Account>?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.getAccounts().enqueue(
            object : Callback<List<Account>>{
                override fun onFailure(call: Call<List<Account>>, t: Throwable) {
                    Log.e("RETROFIT_ERROR", "FAILURE TO LOAD JSON")
                }
                override fun onResponse( call: Call<List<Account>>,
                                         response: Response<List<Account>>) {
                    val accounts = response.body()
                    onResult(accounts)
                }
            }
        )
    }

    fun updateAccount(account: Account, onResult: (Account?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.updateAccount(account).enqueue(
            object : Callback<Account> {
                override fun onFailure(call: Call<Account>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<Account>, response: Response<Account>) {
                    val account = response.body()
                    onResult(account)
                }
            }
        )
    }
}