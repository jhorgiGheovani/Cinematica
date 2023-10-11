package com.jhorgi.cinematica.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenresItem(
    val id : Int,
    val name: String,
) : Parcelable