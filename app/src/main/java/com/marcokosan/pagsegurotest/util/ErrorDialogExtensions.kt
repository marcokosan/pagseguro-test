package com.marcokosan.pagsegurotest.util

import android.content.Context
import androidx.annotation.StringRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.marcokosan.pagsegurotest.R
import com.marcokosan.pagsegurotest.archframework.Result

fun Context.showErrorDialog(
    failure: Result.Failure?,
    closeButtonListener: (() -> Unit)? = null,
    dismissButtonListener: (() -> Unit)? = null,
    tryAgainButtonListener: (() -> Unit)? = null,
    cancelable: Boolean = false
) {
    var title: String? = null
    var message: String? = null

    if (failure?.isConnectionError == true) {
        title = getString(R.string.dialog_no_connection_title)
        message = getString(R.string.dialog_no_connection_message)
    }

    showErrorDialog(
        message = message,
        title = title,
        closeButtonListener = closeButtonListener,
        dismissButtonListener = dismissButtonListener,
        tryAgainButtonListener = tryAgainButtonListener,
        cancelable = cancelable)
}

@Suppress("unused")
fun Context.showErrorDialog(
    @StringRes messageResId: Int,
    @StringRes titleResId: Int? = null,
    closeButtonListener: (() -> Unit)? = null,
    dismissButtonListener: (() -> Unit)? = null,
    tryAgainButtonListener: (() -> Unit)? = null,
    cancelable: Boolean = false
) {
    showErrorDialog(
        message = getText(messageResId),
        title = titleResId?.let { getText(it) },
        closeButtonListener = closeButtonListener,
        dismissButtonListener = dismissButtonListener,
        tryAgainButtonListener = tryAgainButtonListener,
        cancelable = cancelable)
}

fun Context.showErrorDialog(
    message: CharSequence?,
    title: CharSequence?,
    closeButtonListener: (() -> Unit)? = null,
    dismissButtonListener: (() -> Unit)? = null,
    tryAgainButtonListener: (() -> Unit)? = null,
    cancelable: Boolean? = null
) {
    val builder = MaterialAlertDialogBuilder(this)
    builder.setCancelable(cancelable ?: false)
    builder.setTitle(title)
    builder.setMessage(if (message.isNullOrEmpty())
            getString(R.string.generic_error_message) else message)

    when {
        closeButtonListener != null -> {
            builder.setNegativeButton(R.string.close) { _, _ -> closeButtonListener.invoke() }
        }
        dismissButtonListener != null -> {
            cancelable ?: builder.setCancelable(true)
            builder.setNegativeButton(R.string.dismiss)  { _, _ -> dismissButtonListener.invoke() }
        }
        else -> {
            // Um dialog precisa ter pelo menos um botão de fechamento.
            builder.setNegativeButton(R.string.dismiss, null)
        }
    }

    if (tryAgainButtonListener != null) {
        builder.setPositiveButton(R.string.try_again) { _, _ -> tryAgainButtonListener.invoke() }
    }

    builder.show()
}