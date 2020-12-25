package com.marcokosan.pagsegurotest.util

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Build
import android.transition.Transition
import android.transition.TransitionValues
import android.util.AttributeSet
import android.util.Property
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Keep
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class TextSizeTransition : Transition {

    @Keep
    constructor() : super()

    @Keep
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun getTransitionProperties(): Array<String> {
        return TRANSITION_PROPERTIES
    }

    override fun captureStartValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    private fun captureValues(transitionValues: TransitionValues) {
        if (transitionValues.view is TextView) {
            val textView = transitionValues.view as TextView
            transitionValues.values[PROPNAME_TEXT_SIZE] = textView.textSize
        }
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?,
    ): Animator? {
        if (startValues == null || endValues == null) {
            return null
        }
        val startSize = startValues.values[PROPNAME_TEXT_SIZE] as Float
        val endSize = endValues.values[PROPNAME_TEXT_SIZE] as Float

        if (startSize == endSize) {
            return null
        }

        val view = endValues.view as TextView
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, startSize)

        return ObjectAnimator.ofFloat(view, TEXT_SIZE_PROPERTY, startSize, endSize)
    }

    companion object {
        private const val PROPNAME_TEXT_SIZE = "app:transition:textsize"

        private val TRANSITION_PROPERTIES = arrayOf(PROPNAME_TEXT_SIZE)

        private val TEXT_SIZE_PROPERTY: Property<TextView, Float> =
            object : Property<TextView, Float>(Float::class.java, "textSize") {

                override operator fun get(textView: TextView): Float = textView.textSize

                override operator fun set(textView: TextView, textSizePixels: Float) {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePixels)
                }
            }
    }
}