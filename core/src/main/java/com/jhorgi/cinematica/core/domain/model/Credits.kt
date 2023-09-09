package com.jhorgi.cinematica.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Credit(
    val id: Int? = null,
    val name: String? = null,
    val positionOrCharacter: String? = null,
    val imagesPath: String? = null
) : Parcelable