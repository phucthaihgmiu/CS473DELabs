package com.example.mdpassignment6

import com.example.mdpassignment6.data.Account
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("")
    fun getData():Call<List<Account>>
}