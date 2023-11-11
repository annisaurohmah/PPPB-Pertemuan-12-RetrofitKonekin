package com.example.konekin.network

import com.example.konekin.model.ResponseModel
import retrofit2.Call
import com.example.konekin.model.RickModel
import retrofit2.http.GET

interface ApiService {
    ///set untuk method apa
    @GET("data.php") ///akan menjadi parameter yang ditambahkan setelah slash
    fun getRickCharacter(): Call<ResponseModel>
}