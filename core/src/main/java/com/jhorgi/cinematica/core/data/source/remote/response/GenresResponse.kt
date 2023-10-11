package com.jhorgi.cinematica.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.jhorgi.cinematica.core.domain.model.GenresItem

data class GenresResponse(

	@field:SerializedName("genres")
	val genres: List<GenresItem>
)

data class GenresItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

