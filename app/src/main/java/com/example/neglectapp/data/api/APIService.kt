package com.example.neglectapp.data.api

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST("session")
    fun postFile(@Part file: MultipartBody.Part): Call<ResponseBody>

}
