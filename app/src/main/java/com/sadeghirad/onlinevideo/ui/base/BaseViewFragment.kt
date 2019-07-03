package com.sadeghirad.onlinevideo.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.sadeghirad.onlinevideo.root.A
import com.sadeghirad.onlinevideo.root.ApplicationComponent

open class BaseViewFragment : Fragment(), IBaseView {
    fun showShortToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return (activity?.application as A).applicationComponent
    }

    fun showShortSnackbar(message: String) {
        Snackbar.make(
            activity!!.findViewById(android.R.id.content),
            message, Snackbar.LENGTH_SHORT
        ).show()
    }

    fun showLongSnackbar(message: String) {
        Snackbar.make(
            activity!!.findViewById(android.R.id.content),
            message, Snackbar.LENGTH_LONG
        ).show()
    }

    fun showIndefiniteSnackbar(message: String) {
        Snackbar.make(
            activity!!.findViewById(android.R.id.content),
            message, Snackbar.LENGTH_INDEFINITE
        ).show()
    }
}