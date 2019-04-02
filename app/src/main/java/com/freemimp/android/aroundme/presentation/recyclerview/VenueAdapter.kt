package com.freemimp.android.aroundme.presentation.recyclerview

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.freemimp.android.aroundme.domain.Venue

class VenueAdapter : PagedListAdapter<Venue, VenuesViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenuesViewHolder {
       return VenuesViewHolder(parent)
    }

    override fun onBindViewHolder(holder: VenuesViewHolder, position: Int) {
        val venue = getItem(position)

        with(holder) {
            bindTo(venue)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Venue>() {
            override fun areItemsTheSame(oldItem: Venue, newItem: Venue): Boolean {
               return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Venue, newItem: Venue): Boolean {
                return oldItem == newItem
            }
        }
    }
}