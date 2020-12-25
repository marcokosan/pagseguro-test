package com.marcokosan.pagsegurotest.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.marcokosan.pagsegurotest.R

fun ImageView.loadImage(
    model: Any?,
    listener: (() -> Unit)? = null
) {
    val requestListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean,
        ): Boolean {
            listener?.invoke()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?, model: Any?, target: Target<Drawable>?,
            dataSource: DataSource?, isFirstResource: Boolean,
        ): Boolean {
            listener?.invoke()
            return false
        }
    }

    Glide.with(context)
        .load(model)
        .error(R.drawable.ic_missing_image)
        .dontTransform()
        .listener(requestListener)
        .into(this)
}
fun View.setSupportTransitionName(transitionName: String) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        this.transitionName = transitionName
    }
}