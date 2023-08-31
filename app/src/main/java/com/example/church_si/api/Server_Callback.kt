package com.example.church_si.api

interface Server_Callback {
    fun onSuccess(response: String?)

    fun onError(code: String?, error: String?)
}