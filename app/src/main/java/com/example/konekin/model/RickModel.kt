package com.example.konekin.model

import com.google.gson.annotations.SerializedName

class RickModel (
    @SerializedName("data")
    val `data`: List<RickData>
)

data class RickData(
    @SerializedName("id")
    val `id` :Int,
    @SerializedName("name")
    val `employee_name`: String,
    @SerializedName("species")
    val `profile_image` :String
)