package ru.dmisb.sample_api_client.res

data class ResultStatus(
    val status: Int? = null,
    val message: String? = null,
    val messageDev: String? = null,
    val codeResult: Int? = null,
    val duration: Int? = null,
    val idLog: String? = null
) {
    fun isSuccess() = status == 0
}
