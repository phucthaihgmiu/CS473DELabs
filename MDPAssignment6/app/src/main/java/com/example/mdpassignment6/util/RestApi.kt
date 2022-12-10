package com.example.mdpassignment6.util

import com.example.mdpassignment6.data.Account
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface RestApi {

    @GET("/phucthaihgmiu/CS473DELabs/main/MDPAssignment6/public/accounts.json")
    fun getAccounts(): Call<List<Account>>

    @Headers("Content-Type: application/json")
    @PUT("/phucthaihgmiu/CS473DELabs/main/MDPAssignment6/public/accounts.json")
    fun updateAccount(@Body account: Account): Call<Account>
 }