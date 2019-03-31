package com.freemimp.android.aroundme.presentation.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.freemimp.android.aroundme.R
import com.freemimp.android.aroundme.domain.Venue

class VenuesViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(
            R.layout.layout_recyclerview_item_venue,
            parent,
            false
        )
) {
    private var venue: Venue? = null

    fun bindTo(venue: Venue?) {
        this.venue = venue
    }
}