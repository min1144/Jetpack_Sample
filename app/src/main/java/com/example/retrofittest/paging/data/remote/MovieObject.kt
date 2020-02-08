package com.example.retrofittest.paging.data.remote

import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("lastBuildDate") var date: String = "",
    @SerializedName("total") var total: Int = 0,
    @SerializedName("start") var start: Int = 0,
    @SerializedName("display") var display: Int = 0,
    @SerializedName("items") var itemList: List<MovieItem> = emptyList()
)

data class MovieItem(
    @SerializedName("title") var title: String = "",
    @SerializedName("link") var link: String = "",
    @SerializedName("image") var image: String = "",
    @SerializedName("subtitle") var subtitle: String = "",
    @SerializedName("pubDate") var pubDate: String = "",
    @SerializedName("director") var director: String = "",
    @SerializedName("actor") var actor: String = "",
    @SerializedName("userRating") var userRating: String = ""
)