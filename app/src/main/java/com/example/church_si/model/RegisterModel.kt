package com.example.church_si.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterModel(
    @Expose
    @SerializedName("success")
    var success: Boolean,
    @Expose
    @SerializedName("data")
    var data: Data,
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("status")
    var status: Int
)

data class Data(
    @Expose
    @SerializedName("token")
    val token: String,
    @Expose
    @SerializedName("name")
    val name: String
)
