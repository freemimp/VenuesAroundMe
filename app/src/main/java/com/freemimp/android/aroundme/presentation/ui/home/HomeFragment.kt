package com.freemimp.android.aroundme.presentation.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freemimp.android.aroundme.R
import com.freemimp.android.aroundme.domain.Venue
import com.freemimp.android.aroundme.presentation.ViewModelFactory
import com.freemimp.android.aroundme.presentation.recyclerview.VenueAdapter
import com.freemimp.android.aroundme.presentation.ui.home.HomeViewModel
import com.freemimp.android.aroundme.utils.snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: HomeViewModel

    private val recyclerViewAdapter = VenueAdapter()

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
        setupRecyclerView()
        actionButton.setOnClickListener { showVenues() }
        viewModel.venues.observe(this, Observer {pagedListOfVenues ->
            pagedListOfVenues?.let {
                present(pagedListOfVenues)
            }
        })
    }

    private fun present(pagedListOfVenues: PagedList<Venue>) {
recyclerViewAdapter.submitList(pagedListOfVenues)
    }

    private fun setupRecyclerView() {
        venuesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        venuesRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        venuesRecyclerView.adapter = recyclerViewAdapter
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
