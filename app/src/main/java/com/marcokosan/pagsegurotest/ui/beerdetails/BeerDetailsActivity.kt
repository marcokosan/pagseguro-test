package com.marcokosan.pagsegurotest.ui.beerdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.marcokosan.pagsegurotest.R
import com.marcokosan.pagsegurotest.ui.BaseActivity
import com.marcokosan.pagsegurotest.util.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeerDetailsActivity : BaseActivity(R.layout.activity_beer_details) {

    private val viewModel: BeerDetailsViewModel by viewModel()

    companion object {

        private const val EXTRA_BEER_ID = "beer_id"

        fun launch(context: Context, beerId: Long) {
            context.startActivity(starterIntent(context, beerId))
        }

        fun starterIntent(context: Context, beerId: Long): Intent {
            return Intent(context, BeerDetailsActivity::class.java).apply {
                putExtra(EXTRA_BEER_ID, beerId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar()

        viewModel.beerId = intent.getLongExtra(EXTRA_BEER_ID, 0L)

        setupOveservers()
    }

    private fun setupOveservers() {
        viewModel.beerDetail.observe(this, {
            val beerImage = toolbar?.findViewById<ImageView>(R.id.beer_image)
            beerImage?.loadImage(it.imageUrl)
        })
    }
}