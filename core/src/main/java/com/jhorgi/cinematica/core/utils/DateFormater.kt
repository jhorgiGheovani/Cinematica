package com.jhorgi.cinematica.core.utils

import java.text.SimpleDateFormat
import java.util.Locale


fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val outputFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date!!)
    }
