package com.marcokosan.pagsegurotest.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.marcokosan.pagsegurotest.R

fun ImageView.loadImage(
    model: Any?,
    @DrawableRes placeholder: Int = R.drawable.placeholder
) {
    Glide.with(context)
        .load(model)
        .placeholder(placeholder)
        .into(this)
}