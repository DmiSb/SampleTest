package ru.dmisb.sample_api_client.res

import java.util.*

data class GeneralInfoResponse(
    val resultOperation: ResultStatus? = null,
    val data: GeneralInfoData? = null
)
data class GeneralInfoData(
    val typeBonusName: String? = null,
    val currentQuantity: Double? = null,
    val forBurningQuantity: Double? = null,
    val dateBurning: Date? = null
)
