package com.example.church_si.viewModel

import android.app.Activity
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.church_si.api.APIServices
import com.example.church_si.api.AppConstants
import com.example.church_si.api.Server_Callback
import com.example.church_si.utils.CommonFunctions
import com.google.gson.GsonBuilder
import com.google.gson.InstanceCreator
import java.lang.reflect.Type
import com.example.church_si.model.LoginModel
import com.google.gson.Gson

class LoginViewModel:ViewModel() {
    private var loginModelList: MutableLiveData<LoginModel>? = null
    val loginModel: LoginModel? = null
    fun getaboutusApi(
        activity: Activity?,
        view: View?,
        api: String?,
        username: String?,
        password: String?
    ): LiveData<LoginModel?>? {
        loginModelList = MutableLiveData()
        getAboutUsApi(activity!!, view!!, api!!,username,password)
        return loginModelList
    }
//    class UserModelInstanceCreator(var userModelToUpdate: LoginModel? = null)
//        : InstanceCreator<LoginModel> {
//        override fun createInstance(type: Type?): LoginModel {
//            return userModelToUpdate?:LoginModel()
//        }
//
//    }
    private fun getAboutUsApi(activity: Activity, view: View, api: String,username: String?,
                              password: String?) {
        try {
            CommonFunctions.showTProgress(activity)
            var params : HashMap<String, String>
                    = HashMap()

            username?.let { params.put("email" , it) }
            password?.let { params.put("password" , it) }

            APIServices.common_api_call(
                AppConstants.POST,
                api,
                params,
                AppConstants.APPLICATION_FORMURL,
                activity,
                object : Server_Callback {
                    override fun onSuccess(response: String?) {
                        try {

                            var loginModel: LoginModel = Gson().fromJson(
                                response,
                                LoginModel::class.java
                            )
                            loginModelList!!.value = loginModel
                            CommonFunctions.dismissTProgress(activity)
                        } catch (e: Exception) {
                            CommonFunctions.dismissTProgress(activity)
                            e.printStackTrace()
                        }
                    }

                    override fun onError(code: String?, error: String?) {
                        CommonFunctions.dismissTProgress(activity)
                        if (code == "426") {
//                            val ii = Intent(activity, UpdateActivity::class.java)
//                            activity.startActivity(ii)
                        } else {
                            CommonFunctions.showerrorsnackbar(error, activity, view)
                        }
                    }


                })
        } catch (e: Exception) {
            CommonFunctions.dismissTProgress(activity)
            e.printStackTrace()
        }
    }


}