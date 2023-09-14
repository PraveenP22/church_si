package com.example.church_si.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.church_si.MainActivity
import com.example.church_si.R
import com.example.church_si.api.AppConstants
import com.example.church_si.databinding.ActivityLoginBinding
import com.example.church_si.databinding.ActivityRegisterBinding
import com.example.church_si.utils.CommonFunctions
import com.example.church_si.utils.DataManager
import com.example.church_si.viewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var view: View
    private lateinit var viewModel: LoginViewModel
    private  var dataManager: DataManager?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        view = binding.root
        ViewModelProvider(this)[LoginViewModel::class.java].also { viewModel = it }
        dataManager = DataManager(this)

        binding.btnSubmit.setOnClickListener{
            validateRegister()
        }
    }

    fun validateRegister(){
        if(binding.username.text.isNullOrEmpty()){
            CommonFunctions.showerrorsnackbar("Enter username",this,view)
        }else if(!CommonFunctions.isValidMobile(binding.mobileNo.text.toString())){
            CommonFunctions.showerrorsnackbar("Enter valid mobile number",this,view)
        }else if(!CommonFunctions.isValidEmail(binding.email.text.toString())){
            CommonFunctions.showerrorsnackbar("Enter valid mail",this,view)
        }else if(binding.pwd.text.isNullOrEmpty()){
            CommonFunctions.showerrorsnackbar("Enter password",this,view)
        }else if((binding.pwd.text.toString()) != binding.confrmPwd.text.toString()){
            CommonFunctions.showerrorsnackbar("Conform your correct password",this,view)
        }else{
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.postRegisterApi(this@RegisterActivity,view,
                    AppConstants.register,binding.username.text.toString(),binding.email.text.toString(),
                    binding.subscribeNo.text.toString(),
                    binding.mobileNo.text.toString(),
                    binding.pwd.text.toString(),
                    binding.confrmPwd.text.toString(),"1")!!
                    .observe(/* owner = */ this@RegisterActivity) {it
                        if(it?.status == 200){
                            GlobalScope.launch {
                                dataManager?.writeString("NAME",it.data.name)
                                dataManager?.writeString("TOKEN",it.data.token)
//                                dataManager?.writeString("MOBILE",it.data.mobileNo)
                            }
                            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
            }
        }
    }
}