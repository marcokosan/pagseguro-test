package com.marcokosan.pagsegurotest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marcokosan.pagsegurotest.databinding.BeerItemBinding
import com.marcokosan.pagsegurotest.databinding.ListItemPlaceholderBinding
import com.marcokosan.pagsegurotest.model.Beer
import com.marcokosan.pagsegurotest.util.Format
import com.marcokosan.pagsegurotest.util.loadImage

class HomeAdapter(
    private val onNextPage: () -> Unit,
    private val onItemClick: (beer: Beer, sharedViewTransition: BeerItemBinding) -> Unit
) : ListAdapter<Any, RecyclerView.ViewHolder>(ItemDiffCallback()) {

    private var data: List<Beer> = emptyList()
    private var hasNextPage = false

    val isEmpty: Boolean
        get() = data.isEmpty()

    fun setPage(page: BeerPage) {
        data = page.list
        if (hasNextPage && !page.hasNextPage) {
            notifyItemRemoved(data.size)
        }
        hasNextPage = page.hasNextPage
        submitList(ArrayList(data) as List<Any>?)
    }

    override fun getItemCount(): Int {
        return if (hasNextPage) data.size + 1 else data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == data.size) TYPE_PLACEHOLDER else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_PLACEHOLDER -> {
                val view = ListItemPlaceholderBinding.inflate(inflater, parent, false)
                PlaceholderViewHolder(view)
            }
            TYPE_ITEM -> {
                val view = BeerItemBinding.inflate(inflater, parent, false)
                view.root.apply {
                    setOnClickListener {
                        onItemClick.invoke(tag as Beer, view)
                    }
                }
                HomeViewHolder(view)
            }
            else -> throw AssertionError("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PlaceholderViewHolder -> {
                holder.bind(hasNextPage)
                onNextPage.invoke()
            }
            is HomeViewHolder -> holder.bind(data[position])
        }
    }

    private class PlaceholderViewHolder(
        binding: ListItemPlaceholderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(visible: Boolean) {
            itemView.isVisible = visible
        }
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return (oldItem as? Beer)?.id == (newItem as? Beer)?.id
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return (oldItem as? Beer) == (newItem as? Beer)
        }
    }

    companion object {
        private const val TYPE_PLACEHOLDER = 1
        private const val TYPE_ITEM = 2
    }
}

class HomeViewHolder(
    private val binding: BeerItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(beer: Beer) {
        binding.root.tag = beer

        binding.image.loadImage(beer.imageUrl)
        binding.beerName.text = beer.name
        binding.abv.text = Format.DECIMAL_PERCENT.format(beer.abv)
    }
}