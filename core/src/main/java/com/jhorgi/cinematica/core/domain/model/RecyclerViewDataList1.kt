package com.jhorgi.cinematica.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecyclerViewDataList1 (
    val id: Int,
    val tittle: String,
    val posterPath: String? = "",
    val genres: List<String> ?= emptyList()
): Parcelable