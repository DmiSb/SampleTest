package ru.dmisb.sample_api_client.req

data class TokenRequest(
    val idClient: String,
    val accessToken: String = "",
    val paramName: String = "device",
    val paramValue: String,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val sourceQuery: Int = 0
)
