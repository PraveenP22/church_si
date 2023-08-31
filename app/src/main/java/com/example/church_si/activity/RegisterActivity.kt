package com.example.church_si.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.church_si.R
import com.example.church_si.databinding.ActivityLoginBinding
import com.example.church_si.databinding.ActivityRegisterBinding
import com.example.church_si.utils.CommonFunctions

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        view = binding.root
    }

    fun validateRegister(){
        if(binding.username.text.isNullOrEmpty()){
            CommonFunctions.showerrorsnackbar("Enter username",this,view)
        }else if(!CommonFunctions.isValidMobile(binding.mobileNo.text.toString())){
            CommonFunctions.showerrorsnackbar("Enter valid mobile number",this,view)
        }else if(CommonFunctions.isValidEmail(binding.email.text.toString())){
            CommonFunctions.showerrorsnackbar("Enter valid mail",this,view)
        }else if(binding.pwd.text.isNullOrEmpty()){
            CommonFunctions.showerrorsnackbar("Enter password",this,view)
        }else if(!binding.pwd.text!!.equals(binding.confrmPwd.text)){
            CommonFunctions.showerrorsnackbar("Confirm your correct password",this,view)
        }else{

        }
    }
}