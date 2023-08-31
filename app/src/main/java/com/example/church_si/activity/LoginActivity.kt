package com.example.church_si.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.church_si.MainActivity
import com.example.church_si.R
import com.example.church_si.databinding.ActivityLoginBinding
import com.example.church_si.databinding.ActivitySplashBinding
import com.example.church_si.utils.CommonFunctions

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var view: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        view = binding.root

        binding.btnLogin.setOnClickListener{
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.txtRegister.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun validateLogin(){
        if(binding.username.text.isNullOrEmpty()){
            CommonFunctions.showerrorsnackbar("Enter username",this,view)
        }else if(binding.password.text.isNullOrEmpty()){
            CommonFunctions.showerrorsnackbar("Enter password",this,view)
        }else{

        }
    }
}