package com.marcokosan.pagsegurotest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcokosan.pagsegurotest.databinding.FragmentHomeBinding
import com.marcokosan.pagsegurotest.ui.BaseFragment
import com.marcokosan.pagsegurotest.ui.beerdetails.BeerDetailsActivity
import com.marcokosan.pagsegurotest.util.showErrorDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter = HomeAdapter(
        onNextPage = { viewModel.fetchBeers() },
        onItemClick = { id, sharedViewTransition ->
            BeerDetailsActivity.launch(
                requireActivity(),
                id,
                baseActivity.requireToolbar(),
                sharedViewTransition
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)

        setupView()
        setupObservers()

        return binding.root
    }

    private fun setupView() {
        binding.beers.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.failure.observe {
            context?.showErrorDialog(it)
        }

        viewModel.loading.observe { isLoading ->
            if (isLoading) {
                binding.loading.show()
            } else {
                binding.loading.hide()
            }
        }

        viewModel.beers.observe {
            adapter.setList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}