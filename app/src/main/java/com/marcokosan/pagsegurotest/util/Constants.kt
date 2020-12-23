package com.marcokosan.pagsegurotest.util

import java.math.RoundingMode
import java.text.DecimalFormat

object Format {

    val DECIMAL = (DecimalFormat.getNumberInstance(Locale.PT_BR) as DecimalFormat).apply {
        applyPattern("#,##0.##")
        roundingMode = RoundingMode.HALF_UP
    }

    val DECIMAL_PERCENT = (DecimalFormat.getNumberInstance(Locale.PT_BR) as DecimalFormat).apply {
        applyPattern("#,##0.##'%'")
        roundingMode = RoundingMode.HALF_UP
    }
}

object Locale {

    val PT_BR = java.util.Locale("pt", "BR")
}