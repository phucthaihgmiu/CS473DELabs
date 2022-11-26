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

    @GET("/johncodeos-blog/ParseJSONRetrofitConvertersExample/main/array.json")
    suspend fun getEmployees(): Response<List<ArrayJSONModel>>

    @GET("/johncodeos-blog/ParseJSONRetrofitConvertersExample/main/array.json")
    fun getEmployees2(): Call<List<ArrayJSONModel>>

    @GET("/phucthaihgmiu/CS473DELabs/main/MDPAssignment6/accounts.json")
    fun getAccounts(): Call<List<Account>>
 }