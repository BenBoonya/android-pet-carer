package com.boonya.android.library_base.extensions

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun Timestamp.toDisplayFormat(format: String): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(this.toDate())
}