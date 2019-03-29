package com.freemimp.android.aroundme.presentation.ui.home

import android.arch.lifecycle.ViewModel
import com.freemimp.android.aroundme.data.FourSquareApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val fourSquareApi: FourSquareApi) : ViewModel() {

    fun fetchVenues(place: String) {
       CoroutineScope(Dispatchers.IO).launch {
           fourSquareApi.getVenues(place)
       }
    }

    fun validPlace(place: String?): Boolean = !place.isNullOrBlank()
}
