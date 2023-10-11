package com.jhorgi.cinematica.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genres(
    val id : Int,
    val name: String,
) : Parcelable