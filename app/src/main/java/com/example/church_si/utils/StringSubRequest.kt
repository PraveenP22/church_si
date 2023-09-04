package com.example.church_si.utils

import androidx.annotation.GuardedBy
import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

open class StringSubRequest(
    method: Int,
    url: String?,
    listener: Response.Listener<String>?,
    errorListener: Response.ErrorListener?
) : StringRequest(method, url, listener, errorListener) {

    private val lock = Any()

    @GuardedBy("lock")
    private var listener: Response.Listener<String>? = listener

    override fun getParams(): Map<String, String> {
        return params
    }

    override fun cancel() {
        super.cancel()
        synchronized(lock) { listener = null }
    }

    override fun deliverResponse(response: String) {
        var listener: Response.Listener<String>?
        synchronized(lock) { listener = this.listener }
        if (listener != null) {
            listener!!.onResponse(response)
        }
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<String> {
        val parsed: String = try {
            String(response.data, Charset.forName(HttpHeaderParser.parseCharset(response?.headers)))
        } catch (e: UnsupportedEncodingException) {
            String(response.data)
        }

        return Response.success(
            parsed,
            HttpHeaderParser.parseCacheHeaders(response)
        )
    }

}