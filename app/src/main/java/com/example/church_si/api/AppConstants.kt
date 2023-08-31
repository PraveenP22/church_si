package com.example.church_si.api

import com.android.volley.Request

class AppConstants {
    val BASE_URL: String = BuildConfig.BASE_URL


    val APPLICATION_FORMURL = "application/x-www-form-urlencoded"
    val APPLICATION_JSON = "application/json"
    var GET = Request.Method.GET
    var POST = Request.Method.POST
    var PUT = Request.Method.PUT
    var DELETE = Request.Method.DELETE

    val playstoreLink = "https://play.google.com/store/apps/details?id=com.izamedia.hz"

    val faq = "api/store-finder/ajax/faq/"
    val store_finder = "api/store-finder/"


}