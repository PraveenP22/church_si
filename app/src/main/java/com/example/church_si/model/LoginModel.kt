package com.example.church_si.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginModel(
    @Expose
@SerializedName("success")
var success: Boolean,
@Expose
@SerializedName("data")
var data: Datas,
@Expose
@SerializedName("message")
val message: String,
@Expose
@SerializedName("status")
var status: Int
)
data class Datas(
    @Expose
    @SerializedName("token")
    val token: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("mobile_no")
    val mobileNo: String
)
