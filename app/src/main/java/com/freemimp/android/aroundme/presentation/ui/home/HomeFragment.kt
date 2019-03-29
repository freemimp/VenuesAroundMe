package com.freemimp.android.aroundme.presentation.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freemimp.android.aroundme.R
import com.freemimp.android.aroundme.presentation.ViewModelFactory
import com.freemimp.android.aroundme.presentation.ui.home.HomeViewModel
import com.freemimp.android.aroundme.utils.snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.home_fragment.actionButton
import kotlinx.android.synthetic.main.home_fragment.placesTextView
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionButton.setOnClickListener { showVenues() }
    }

   private fun showVenues() {
       val place = placesTextView.text.toString()
       if (viewModel.validPlace(place)) {
           viewModel.fetchVenues(place)
       } else {
            snackbar(getString(R.string.error_invalid_place))
       }
   }

    companion object {
        fun newInstance() = HomeFragment()
    }

}
