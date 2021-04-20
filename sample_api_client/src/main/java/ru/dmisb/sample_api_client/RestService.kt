package ru.dmisb.sample_api_client

import retrofit2.http.*
import ru.dmisb.sample_api_client.req.TokenRequest
import ru.dmisb.sample_api_client.res.GeneralInfoResponse
import ru.dmisb.sample_api_client.res.TokenResponse

interface RestService {
    @POST("api/v3/clients/accesstoken")
    suspend fun getAccessToken(
        @Header("AccessKey") accessKey: String,
        @Body tokenRequest: TokenRequest
    ): TokenResponse

    @GET("api/v3/ibonus/generalinfo/{AccessToken}")
    suspend fun getGeneralInfo(
        @Header("AccessKey") accessKey: String,
        @Path("AccessToken") accessToken: String
    ): GeneralInfoResponse
}