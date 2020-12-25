package com.marcokosan.pagsegurotest.ui.home

import com.marcokosan.pagsegurotest.model.Beer

class BeerPage {

    private val _list: ArrayList<Beer> = ArrayList(0)
    val list: List<Beer> get() = _list

    var currentPage = 0
        private set

    var hasNextPage = false
        private set

    fun addPage(data: List<Beer>) {
        if (data.isEmpty()) {
            hasNextPage = false
        } else {
            _list.addAll(data)
            currentPage++
            hasNextPage = true
        }
    }
}