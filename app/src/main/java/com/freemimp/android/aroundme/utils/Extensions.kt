package com.freemimp.android.aroundme.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

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


