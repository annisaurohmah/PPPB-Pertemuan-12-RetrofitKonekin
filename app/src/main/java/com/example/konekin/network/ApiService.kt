package com.example.konekin.network

import retrofit2.Call
import com.example.konekin.model.RickModel
import retrofit2.http.GET

interface ApiService {
    ///set untuk method apa
    @GET("character") ///akan menjadi parameter yang ditambahkan setelah slash
    fun getRickCharacter(): Call<RickModel>
}