package com.freemimp.android.aroundme.presentation.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.freemimp.android.aroundme.R
import com.freemimp.android.aroundme.domain.Venue
import kotlinx.android.synthetic.main.layout_recyclerview_item_venue.view.addressTextView
import kotlinx.android.synthetic.main.layout_recyclerview_item_venue.view.categoryTextView
import kotlinx.android.synthetic.main.layout_recyclerview_item_venue.view.nameTextView

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

        itemView.nameTextView.text = venue?.name
        itemView.categoryTextView.text = venue?.type
        itemView.addressTextView.text = "${venue?.address?.address}, ${venue?.address?.city}, ${venue?.address?.postalCode} "
    }
}