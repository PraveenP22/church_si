package com.example.church_si.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.church_si.MainActivity
import com.example.church_si.api.AppConstants
import com.example.church_si.databinding.ActivityLoginBinding
import com.example.church_si.utils.CommonFunctions
import com.example.church_si.utils.DataManager
import com.example.church_si.viewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var view: View
    private lateinit var viewModel:LoginViewModel
    private  var dataManager: DataManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        view = binding.root
        ViewModelProvider(this)[LoginViewModel::class.java].also { viewModel = it }
        dataManager = DataManager(this)

        binding.btnLogin.setOnClickListener{
           validateLogin()
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
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.getaboutusApi(this@LoginActivity,view,AppConstants.login,binding.username.text.toString(),binding.password.text.toString())!!
                    .observe(/* owner = */ this@LoginActivity) {it
                        if(it?.status == 200){
                            GlobalScope.launch {
                                dataManager?.writeString("NAME",it.data.name)
                                dataManager?.writeString("TOKEN",it.data.token)
                                dataManager?.writeString("MOBILE",it.data.mobileNo)
                            }
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
            }

        }
    }
}