package com.marcokosan.pagsegurotest.ui

import android.os.Bundle
import com.marcokosan.pagsegurotest.ui.home.HomeActivity

class LauncherActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeActivity.launch(this)
        finish()
    }
}