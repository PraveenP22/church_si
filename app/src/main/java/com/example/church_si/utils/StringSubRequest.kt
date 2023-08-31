package com.example.church_si.utils

import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import java.nio.charset.StandardCharsets

open class StringSubRequest(
    method: Int,
    url: String?,
    listener: Response.Listener<String>?,
    errorListener: Response.ErrorListener?
) : StringRequest(method, url, listener, errorListener) {

    override fun parseNetworkResponse(response: NetworkResponse): Response<String?>? {
        val utf8String = String(response.data, StandardCharsets.UTF_8)
        return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response))
    }

}