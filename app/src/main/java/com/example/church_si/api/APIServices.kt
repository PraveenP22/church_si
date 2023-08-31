package com.example.church_si.api

import android.os.Build
import android.util.Log
import androidx.core.os.BuildCompat
import com.android.volley.DefaultRetryPolicy
import com.android.volley.NetworkResponse
import com.android.volley.ServerError
import com.android.volley.TimeoutError
import com.android.volley.toolbox.HttpHeaderParser
import com.example.church_si.utils.AppController
import com.example.church_si.utils.CommonFunctions
import com.example.church_si.utils.StringSubRequest
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset

class APIServices : AppController() {
    /*
     HTTP_REST_MESSAGES = {"200": _("Success"),
                           "400": _("Failed"),
                           "401": _("Authentication Failed"),
                           "426": _("Version Mismatch"),
                           "429": _("Too many requests"),
                           "500": _("Internal server error")}
     */
    fun common_api_call(
        method: Int,
        url: String,
        data: Map<String?, String?>,
        datatype: String,
        isauthrequired: Boolean,
        callback: Server_Callback
    ) {
        var URL: String = BuildConfig.BASE_URL + url
        URL = if (URL.contains("?")) {
            URL + "&ver=" + BuildConfig.VERSION_NAME + "&source=Android_Customer_Mobile_Application"
        } else {
            URL + "?ver=" + BuildConfig.VERSION_NAME + "&source=Android_Customer_Mobile_Application"
        }
        println("URL $URL")
        val signinRequest: StringSubRequest = object : StringSubRequest(method, URL, { response ->
            Log.i("success response  ", response)
            try {
                callback.onSuccess(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, { volleyError ->
            val response: NetworkResponse = volleyError.networkResponse
            println("failure response  $response")
            if(response.toString() != "null") {
                try {
                    val res = String(
                        response.data,
                        Charset.forName(HttpHeaderParser.parseCharset(response.headers))
                    )
                    val obj = JSONObject(res)
                    println("error response $obj")
                    val message = obj.getString("message")
                    val errors = obj.getString("errors")
                    val code = obj.getString("status_code")
                    println("empty null " + (errors == "null") + " " + errors)
                    if (code == "426") {
                        callback.onError(code, obj.toString())
                    } else if (code == "426") {
                        callback.onError(code, message)
                    } else if (code == "400") {
                        callback.onError(code, message)
                    } else if (code == "401") {
                        callback.onError(code, "Authentication Failed")
                    } else if (code == "429") {
                        callback.onError(code, "Too many requests")
                    } else if (code == "500") {
                        callback.onError(code, "Internal server error")
                    } else if (errors == "null" || errors == "") {
                        callback.onError(code, message)
                    } else {
                        val jsonObject = JSONObject(errors)
                        if (jsonObject.length() != 0) {
                            val keys = jsonObject.keys()
                            while (keys.hasNext()) {
                                val key = keys.next()
                                if (jsonObject[key] is JSONArray) {
                                    println("key $key")
                                    val jsonArray = jsonObject.getJSONArray(key)
                                    callback.onError(code, jsonArray[0].toString())
                                    break
                                }
                            }
                        } else {
                            callback.onError(code, message)
                        }
                    }
                } catch (e1: Exception) {
                    e1.printStackTrace()
                    callback.onError("", "Something went wrong")
                }
            } else if (volleyError is TimeoutError) {
                callback.onError("code", "Request Timeout")
            } else if (volleyError is ServerError) {
                callback.onError("", "Server Error")
            } else {
                callback.onError("", "Something went wrong")
            }
        }) {

            override fun getParams(): Map<String?, String?> {
                println("getParams $data")
                return data
            }

            override fun getHeaders(): MutableMap<String, String> {
                val header: MutableMap<String, String> = HashMap()
                if (isauthrequired) {
                    Log.i("Auth", "Authorization " + "Token " + CommonFunctions.authToken)
                    header["Authorization"] = "Token " + CommonFunctions.authToken
                } else {
                    if (CommonFunctions.accessToken != null && !CommonFunctions.authToken.isEmpty()) {
                        Log.i("Auth", "Authorization " + "Bearer " + CommonFunctions.authToken)
                        header["Authorization"] = "Bearer " + CommonFunctions.authToken
                    }
                }
                if (datatype != "") {
                    header["Content-Type"] = datatype
                }
                return header
            }

        }
        signinRequest.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 24,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        getInstance()?.addToRequestQueue(signinRequest)
    }


}