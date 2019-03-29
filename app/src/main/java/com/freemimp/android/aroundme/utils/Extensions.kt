package com.freemimp.android.aroundme.utils

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Fragment.snackbar(msg: String, view: View? = activity?.findViewById(android.R.id.content)) {
    view?.let {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
    }
}

fun Fragment.hideKeyboard(focusView: View? = null) {
    val view = focusView ?: activity?.currentFocus
    view?.let {
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}


