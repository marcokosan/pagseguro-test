package com.marcokosan.pagsegurotest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marcokosan.pagsegurotest.databinding.BeerItemBinding
import com.marcokosan.pagsegurotest.model.Beer
import com.marcokosan.pagsegurotest.util.Format
import com.marcokosan.pagsegurotest.util.loadImage

class HomeAdapter(
    private val onNextPage: () -> Unit,
    private val onItemClick: (id: Long) -> Unit
) : ListAdapter<Beer, HomeViewHolder>(ItemDiffCallback()) {

    private var data: List<Beer> = emptyList()

    fun setList(list: List<Beer>) {
        data = list
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = BeerItemBinding.inflate(inflater, parent, false)

        view.root.apply {
            setOnClickListener {
                onItemClick.invoke(tag as Long)
            }
        }
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<Beer>() {

        override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem == newItem
        }
    }
}

class HomeViewHolder(
    private val binding: BeerItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(beer: Beer) {
        binding.root.tag = beer.id

        binding.image.loadImage(beer.imageUrl)
        binding.name.text = beer.name
        binding.abv.text = Format.DECIMAL_PERCENT.format(beer.abv)
    }
}