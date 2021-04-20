package ru.dmisb.sample_api_client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.dmisb.sample_api_client.req.TokenRequest
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://mp1.iprobonus.com/"
private const val ACCESS_KEY = "891cf53c-01fc-4d74-a14c-592668b7a03c"

class SampleApiClient {
    private val api : RestService by lazy {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient().newBuilder()
            .readTimeout(2, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(JsonConverter.moshi))
            .baseUrl(BASE_URL)
            .build()

        retrofit.create(RestService::class.java)
    }

    suspend fun getAccessToken(clientId: String, deviceId: String) =
            api.getAccessToken(ACCESS_KEY, TokenRequest(idClient = clientId, paramValue = deviceId))

    suspend fun getGeneralInfo(accessToken: String) =
            api.getGeneralInfo(ACCESS_KEY, accessToken)
}