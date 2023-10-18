package com.jhorgi.cinematica.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecyclerViewDataList2(
    val id: Int,
    val tittle: String,
    val posterPath: String? = "",
    val overview: String,
    val releaseDate: String? ="",
    val genres: List<String> ?= null
) : Parcelable