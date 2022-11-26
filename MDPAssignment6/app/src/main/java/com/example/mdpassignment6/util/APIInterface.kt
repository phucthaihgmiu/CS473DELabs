package com.example.mdpassignment6.util

import com.example.mdpassignment6.data.Account
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {
//    @GET("/Oclemy/SampleJSON/338d9585/spacecrafts.json")
    @GET("/phucthaihgmiu/CS473DELabs/main/MDPAssignment6/accounts.json")
    suspend fun getData():Response<List<Account>>
}