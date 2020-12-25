package com.marcokosan.pagsegurotest.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

abstract class BaseFragment(
    @LayoutRes contentLayoutId: Int = 0
) : Fragment(contentLayoutId) {

    val baseActivity: BaseActivity
        get() = activity as BaseActivity

    fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
        observe(viewLifecycleOwner, observer)
    }
}