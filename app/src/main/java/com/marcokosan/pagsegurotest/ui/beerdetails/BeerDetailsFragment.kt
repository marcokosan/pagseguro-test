package com.marcokosan.pagsegurotest.ui.beerdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.marcokosan.pagsegurotest.databinding.FragmentBeerDetailsBinding
import com.marcokosan.pagsegurotest.ui.BaseFragment
import com.marcokosan.pagsegurotest.util.Format
import com.marcokosan.pagsegurotest.util.loadImage
import com.marcokosan.pagsegurotest.util.showErrorDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BeerDetailsFragment : BaseFragment() {

    private val viewModel: BeerDetailsViewModel by sharedViewModel()

    private var _binding: FragmentBeerDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeerDetailsBinding.inflate(inflater)
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.failure.observe {
            context?.showErrorDialog(
                failure = it,
                tryAgainButtonListener = { viewModel.loadBeer() }
            )
        }

        viewModel.loading.observe { isLoading ->
            if (isLoading) {
                binding.content.isVisible = false
                binding.loading.show()
            } else {
                binding.loading.hide()
            }
        }

        viewModel.beerDetail.observe { data ->
            binding.apply {
                image.loadImage(data.imageUrl)
                name.text = data.name
                abv.text = Format.DECIMAL_PERCENT.format(data.abv)
                ibu.text =  Format.DECIMAL.format(data.ibu)
                tagline.text = data.tagline
                description.text = data.description

                content.isVisible = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}