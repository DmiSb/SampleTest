package ru.dmisb.sample_api_client

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.text.SimpleDateFormat
import java.util.*

object JsonConverter {

    val moshi = Moshi.Builder()
        .add(DateAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()

    class DateAdapter {
        @ToJson
        fun toJson(date: Date) : String {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
            return format.format(date)
        }

        @FromJson
        fun fromJson(json: String) : Date? {
            return try {
                val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
                format.parse(json)
            } catch (t: Throwable) {
                null
            }
        }
    }
}