package com.freemimp.android.aroundme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.freemimp.android.aroundme.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance())
                    .commitNow()
        }
    }

}
