package com.example.church_si.utils

import android.app.Application
import android.content.Context
import android.text.TextUtils
import androidx.multidex.MultiDex
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

open class AppController : Application() {
    private val TAG: String = AppController::class.java.simpleName

    private var mRequestQueue: RequestQueue? = null
    private var mImageLoader: ImageLoader? = null

    /*
        private static FirebaseAnalytics mFirebaseAnalytics;
        private static GoogleAnalytics sAnalytics;
        private static Tracker sTracker;*/
    private lateinit var mInstance: AppController

    override fun onCreate() {
        super.onCreate()
        mInstance = this /*
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        sAnalytics = GoogleAnalytics.getInstance(this);*/
    }



    private fun getRequestQueue(): RequestQueue? {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(applicationContext)
        }
        return mRequestQueue
    }

//    fun getImageLoader(): ImageLoader? {
//        getRequestQueue()
//        if (mImageLoader == null) {
//            mImageLoader = ImageLoader(mRequestQueue, LruBitmapCache())
//        }
//        return mImageLoader
//    }

    fun <T> addToRequestQueue(req: Request<T>, tag: String?) {
        // set the default tag if tag is empty
        req.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        getRequestQueue()?.add(req)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        req.tag = TAG
        getRequestQueue()?.add(req)
    }

    fun cancelPendingRequests(tag: Any?) {
        if (mRequestQueue != null) {
            mRequestQueue?.cancelAll(tag)
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        private var mInstance: AppController? = null
        fun getInstance() = mInstance
    }


}