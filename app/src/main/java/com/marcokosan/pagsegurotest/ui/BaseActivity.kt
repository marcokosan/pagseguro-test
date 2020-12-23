package com.marcokosan.pagsegurotest.ui

import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.marcokosan.pagsegurotest.R

abstract class BaseActivity(
    @LayoutRes contentLayoutId: Int = 0,
) : AppCompatActivity(contentLayoutId) {

    private var toolbar: Toolbar? = null

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun setupToolbar(@IdRes id: Int? = null, displayHomeAsUp: Boolean = true) {
        toolbar = findViewById(id ?: R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUp)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}