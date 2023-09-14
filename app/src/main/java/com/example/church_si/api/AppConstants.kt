package com.example.church_si.api

import com.android.volley.Request
import com.example.church_si.BuildConfig

class AppConstants {
    companion object {

        val BASE_URL: String = BuildConfig.BASE_URL

        const val jwtverify = ""
        const val jwtrefresh = ""

        const val APPLICATION_FORMURL = "application/x-www-form-urlencoded"
        const val APPLICATION_JSON = "application/json"
        const val APP_SOURCE = "Android Customer Mobile Application"
        const val APP_MODE = "ANDROID_APP"
        var GET = Request.Method.GET
        var POST = Request.Method.POST
        var PUT = Request.Method.PUT
        var DELETE = Request.Method.DELETE

        const val login = "api/login"
        const val register = "api/register"

    }
}