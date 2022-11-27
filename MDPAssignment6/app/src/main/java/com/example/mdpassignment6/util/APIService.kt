package com.example.mdpassignment6.util

import com.example.mdpassignment6.data.Account
import com.example.mdpassignment6.data.ArrayJSONModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface APIService {


    /*
       Array JSON
    */
    @GET("/phucthaihgmiu/CS473DELabs/main/MDPAssignment6/public/accounts.json")
    fun getAccounts(): Call<List<Account>>
 }