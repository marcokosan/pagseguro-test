package com.marcokosan.pagsegurotest.ui.beerdetails

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.marcokosan.pagsegurotest.R
import com.marcokosan.pagsegurotest.databinding.BeerItemBinding
import com.marcokosan.pagsegurotest.model.Beer
import com.marcokosan.pagsegurotest.ui.BaseActivity
import com.marcokosan.pagsegurotest.util.loadImage
import com.marcokosan.pagsegurotest.util.setSupportTransitionName
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeerDetailsActivity : BaseActivity(R.layout.activity_beer_details) {

    private val viewModel: BeerDetailsViewModel by viewModel()

    private var beerImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()
        setupToolbar()

        intent.getParcelableExtra<Beer>(EXTRA_BEER)?.let {
            viewModel.setBeer(it)
        }

        setupViews()
        setupOveservers()
    }

    private fun setupViews() {
        beerImage = toolbar?.findViewById(R.id.beer_image)
    }

    private fun setupOveservers() {
        viewModel.beerDetail.observe(this, {
            beerImage?.apply {
                setSupportTransitionName(
                    getString(R.string.transition_beer_image, it.id)
                )

                loadImage(
                    it.imageUrl,
                    listener = { supportStartPostponedEnterTransition() }
                )
            }
        })
    }

    companion object {

        private const val EXTRA_BEER = "beer"

        fun launch(
            activity: Activity,
            beer: Beer,
            toolbar: View,
            sharedViewTransition: BeerItemBinding
        ) {
            activity.apply {
                val intent = starterIntent(this, beer)

                val imageTransitionName = getString(R.string.transition_beer_image, beer.id)

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    Pair(toolbar, getString(R.string.transition_toolbar)),
                    Pair(sharedViewTransition.image, imageTransitionName),
                )

                startActivity(intent, options.toBundle())
            }
        }

        fun starterIntent(context: Context, beer: Beer): Intent {
            return Intent(context, BeerDetailsActivity::class.java).apply {
                putExtra(EXTRA_BEER, beer)
            }
        }
    }
}