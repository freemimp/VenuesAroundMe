package com.freemimp.android.aroundme.presentation.pagination

import android.arch.paging.DataSource
import com.freemimp.android.aroundme.domain.Venue
import javax.inject.Inject

class VenueDataSourceFactory @Inject constructor(
    private val dataSource: VenueDataSource
) : DataSource.Factory<Int, Venue>() {
    override fun create(): DataSource<Int, Venue> = dataSource
}