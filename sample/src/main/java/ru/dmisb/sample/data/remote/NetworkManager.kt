package ru.dmisb.sample.data.remote

import ru.dmisb.sample_api_client.SampleApiClient
import ru.dmisb.sample_api_client.res.GeneralInfoData

private const val CLIENT_ID = "2c44d8c2-c89a-472e-aab3-9a8a29142315"
private const val DEVICE_ID = "7db72635-fd0a-46b9-813b-1627e3aa02ea"

object NetworkManager {

    private val httpClient by lazy {
        SampleApiClient()
    }

    suspend fun getGeneralInfo() : GeneralInfoData? {
        val res = httpClient.getAccessToken(CLIENT_ID, DEVICE_ID)
        return if (res.result?.isSuccess() == true && !res.accessToken.isNullOrEmpty()) {
            val accessToken = res.accessToken.orEmpty()
            httpClient.getGeneralInfo(accessToken).data
        } else {
            null
        }
    }
}