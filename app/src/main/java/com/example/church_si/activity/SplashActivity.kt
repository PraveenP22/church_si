package com.example.church_si.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import com.example.church_si.MainActivity
import com.example.church_si.R
import com.example.church_si.databinding.ActivitySplashBinding
import com.example.church_si.utils.DataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread as Thread

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var dataManager: DataManager
    private lateinit var  token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataManager = DataManager(this)

        Glide.with(this@SplashActivity).asGif().load(R.raw.flash).into(binding.splash);


        GlobalScope.launch(Dispatchers.Main) {
            dataManager?.readString("TOKEN")?.asLiveData()?.observe(this@SplashActivity) {
                token = it.toString()

            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            if (!token.isNullOrEmpty()) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 2500)
    }

}