package com.example.church_si.api

import java.lang.Boolean

class BuildConfig {

    companion object {
        val BASE_URL: String = "http://church.aicodingbees.com/"
        val DEBUG = Boolean.parseBoolean("true")
        val APPLICATION_ID = "com.izamedia.playschool"
        val BUILD_TYPE = "debug"
        val FLAVOR = "tst"
        val VERSION_CODE = 1
        val VERSION_NAME = "0.0.8"

        // Field from product flavor: tst
        val APP_ENV = "TST"

        // Field from product flavor: tst
        val APP_PACKAGE = "com.izamedia.playschool"

    }
}