package com.jhorgi.cinematica.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvDetailsResponse(

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	@field:SerializedName("number_of_episodes")
	val numberOfEpisodes: Int? = null,

	@field:SerializedName("networks")
	val networks: List<NetworksItem>? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("genres")
	val genres: List<GenresTvItem>? = null,

	@field:SerializedName("popularity")
	val popularity: Any? = null,

	@field:SerializedName("production_countries")
	val productionCountries: List<ProductionCountriesTvItem>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("number_of_seasons")
	val numberOfSeasons: Int? = null,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null,

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("seasons")
	val seasons: List<SeasonsItem?>? = null,

	@field:SerializedName("languages")
	val languages: List<String?>? = null,

	@field:SerializedName("created_by")
	val createdBy: List<Any?>? = null,

	@field:SerializedName("last_episode_to_air")
	val lastEpisodeToAir: LastEpisodeToAir? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("origin_country")
	val originCountry: List<String?>? = null,

	@field:SerializedName("spoken_languages")
	val spokenLanguages: List<SpokenLanguagesTvItem?>? = null,

	@field:SerializedName("production_companies")
	val productionCompanies: List<Any?>? = null,

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("tagline")
	val tagline: String? = null,

	@field:SerializedName("episode_run_time")
	val episodeRunTime: List<Int?>? = null,

	@field:SerializedName("adult")
	val adult: Boolean? = null,

	@field:SerializedName("next_episode_to_air")
	val nextEpisodeToAir: Any? = null,

	@field:SerializedName("in_production")
	val inProduction: Boolean? = null,

	@field:SerializedName("last_air_date")
	val lastAirDate: String? = null,

	@field:SerializedName("homepage")
	val homepage: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class NetworksItem(

	@field:SerializedName("logo_path")
	val logoPath: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("origin_country")
	val originCountry: String? = null
)

data class LastEpisodeToAir(

	@field:SerializedName("episode_type")
	val episodeType: String? = null,

	@field:SerializedName("production_code")
	val productionCode: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("show_id")
	val showId: Int? = null,

	@field:SerializedName("season_number")
	val seasonNumber: Int? = null,

	@field:SerializedName("runtime")
	val runtime: Int? = null,

	@field:SerializedName("still_path")
	val stillPath: Any? = null,

	@field:SerializedName("air_date")
	val airDate: String? = null,

	@field:SerializedName("episode_number")
	val episodeNumber: Int? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null
)

data class ProductionCountriesTvItem(

	@field:SerializedName("iso_3166_1")
	val iso31661: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class SpokenLanguagesTvItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("iso_639_1")
	val iso6391: String? = null,

	@field:SerializedName("english_name")
	val englishName: String? = null
)

data class SeasonsItem(

	@field:SerializedName("air_date")
	val airDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("episode_count")
	val episodeCount: Int? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("season_number")
	val seasonNumber: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("poster_path")
	val posterPath: Any? = null
)

data class GenresTvItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
