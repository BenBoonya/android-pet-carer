package com.boonya.android.library_base.extensions

fun Int.toDisplayDigit(): String = if (this <= 9) "0$this" else this.toString()