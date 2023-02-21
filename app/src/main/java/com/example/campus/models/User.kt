package com.example.campus.models


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("bio")
    val bio: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("uci_netid")
    val uciNetid: String
)