package com.freemimp.android.aroundme.presentation.ui.home

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.freemimp.android.aroundme.R
import com.freemimp.android.aroundme.domain.Venue
import com.freemimp.android.aroundme.presentation.ViewModelFactory
import com.freemimp.android.aroundme.presentation.pagination.Place
import com.freemimp.android.aroundme.presentation.pagination.VenueDataSource
import com.freemimp.android.aroundme.presentation.recyclerview.VenueAdapter
import com.freemimp.android.aroundme.utils.hideKeyboard
import com.freemimp.android.aroundme.utils.snackbar
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.home_fragment.actionButton
import kotlinx.android.synthetic.main.home_fragment.placesTextView
import kotlinx.android.synthetic.main.home_fragment.venuesRecyclerView
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var place: Place

    private lateinit var viewModel: HomeViewModel

    private val recyclerViewAdapter = VenueAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        viewModel.venues.observe(this, Observer { pagedListOfVenues ->
            pagedListOfVenues?.let {
                present(pagedListOfVenues)
            }
        })

        viewModel.error.observe(this, Observer { error ->
            error.getContentIfNotHandled()?.let {
                snackbar(it)
            }
        })

        viewModel.sourceError.observe(this, Observer { error ->
            error.getContentIfNotHandled()?.let {
                showSnackbar(it)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        actionButton.setOnClickListener { showVenues() }
    }

    private fun showSnackbar(sourceError: VenueDataSource.Error) {
        Snackbar.make(activity!!.findViewById(android.R.id.content),
                R.string.generic_network_response_error,
                Snackbar.LENGTH_INDEFINITE).setAction(R.string.retry) {
            retry(sourceError)
        }.show()
    }

    private fun present(pagedListOfVenues: PagedList<Venue>) {
        recyclerViewAdapter.submitList(pagedListOfVenues)
    }

    private fun setupRecyclerView() {
        venuesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        venuesRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        venuesRecyclerView.adapter = recyclerViewAdapter
    }

    private fun retry(error: VenueDataSource.Error) {
        error.retry()
    }

    private fun showVenues() {
        hideKeyboard()
        val query = placesTextView.text.toString()
        place.query = query
        viewModel.fetchVenues(query)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}
