package com.marcokosan.pagsegurotest.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.marcokosan.pagsegurotest.R
import com.marcokosan.pagsegurotest.ui.BaseActivity

class HomeActivity : BaseActivity(R.layout.activity_home) {

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar(displayHomeAsUp = false)
    }
}