package com.example.church_si.utils

import android.app.Activity
import android.content.Context
import android.location.LocationManager
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.church_si.R
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class CommonFunctions {

    companion object{
        var authToken = ""
        var accessToken = ""
        private var fragmentManager: FragmentManager? = null
        var dialogDismissed = false
         fun isValidEmail(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isGpsOn(context: Context): Boolean {
            var gps = false
            val manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (manager != null) {
                gps =
                    manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && manager.isProviderEnabled(
                        LocationManager.NETWORK_PROVIDER
                    )
            }
            return gps
        }

        fun showerrorsnackbar(Message: String?, activity: Activity, Parentlayout: View?) {
            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            val view = activity.currentFocus
            if (imm != null) {
                if (view != null) {
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
            val snackbar = Snackbar.make(Parentlayout!!, Message!!, Snackbar.LENGTH_LONG)
            snackbar.view.setBackgroundColor(ContextCompat.getColor(activity, R.color.grey))
            snackbar.show()
        }

        fun showsuccesssnackbar(Message: String?, context: Context?, Parentlayout: View?) {
            val snackbar = Snackbar.make(Parentlayout!!, Message!!, Snackbar.LENGTH_LONG)
            snackbar.view.setBackgroundColor(ContextCompat.getColor(context!!, R.color.purple_200))
            snackbar.show()
        }

         fun isValidMobile(phone: String): Boolean {
            return if (!Pattern.matches("[a-zA-Z]+", phone)) {
                phone.length > 9 && phone.length <= 10
            } else false
        }

        fun calculateNoOfColumns(
            context: Context
        ): Int { // For example columnWidthdp=180
            val displayMetrics = context.resources.displayMetrics
            val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
            return (screenWidthDp / 190 + 0.5).toInt()
        }

        fun showTProgress(context: Context) {
            fragmentManager = (context as AppCompatActivity).supportFragmentManager
            val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
            val newFragment: DialogFragment = LottieDialogFragment.newInstance()!!
            dialogDismissed = false
            newFragment.isCancelable = false
            newFragment.show(ft, "dialog")
        }

        fun dismissTProgress(context: Context) {
            dialogDismissed = true
            val prev = fragmentManager!!.findFragmentByTag("dialog")
            if (!fragmentManager!!.isStateSaved) {
                if (prev != null) {
                    val df = prev as LottieDialogFragment?
                    df?.dismiss()
                    fragmentManager!!.beginTransaction().remove(prev).commitAllowingStateLoss()
                }
            }
        }


    }




}