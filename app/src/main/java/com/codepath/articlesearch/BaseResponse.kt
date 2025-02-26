package com.codepath.articlesearch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    @SerialName("docs")
    val docs: List<Article>?
)
