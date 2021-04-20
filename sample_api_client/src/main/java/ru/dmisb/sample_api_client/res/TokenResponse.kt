package ru.dmisb.sample_api_client.res

data class TokenResponse(
    val result: ResultStatus? = null,
    val accessToken: String? = null
)
