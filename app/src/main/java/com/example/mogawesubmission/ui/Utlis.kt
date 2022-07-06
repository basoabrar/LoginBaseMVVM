package com.example.mogawesubmission.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mogawesubmission.data.network.Res
import com.example.mogawesubmission.ui.auth.LoginFragment
import com.google.android.material.snackbar.Snackbar

fun <A : Activity> Activity.starNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("retry") {
            it()
        }
        snackbar.show()
    }

}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE

}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}



fun Fragment.handleApiError(
    failure: Res.Fail,
    retry: (() -> Unit)? = null
) {
    when {


        failure.isNetworkError -> requireView().snackbar("Cek Koneksi Internet Anda", retry)
        failure.errorCode == 401 -> {
            if (this is LoginFragment) {
                requireView().snackbar("email dan passord salah")
            } else {
                //logout
            }
        }else -> {
            val error = failure.errorBody?.toString()
             requireView().snackbar(error!!)

        }

    }

}