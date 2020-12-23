package com.marcokosan.pagsegurotest.provider.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.ResolverStyle

@Suppress("unused")
object PagSeguroAdapter {

    private val LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss")
        .withResolverStyle(ResolverStyle.STRICT)

    private val LOCAL_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm")

    private val LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd")
        .withResolverStyle(ResolverStyle.STRICT)

    @FromJson
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return if (value.isNullOrEmpty())
            null else LocalDateTime.parse(value, LOCAL_DATE_TIME_FORMATTER)
    }

    @ToJson
    fun fromZonedDateTime(value: LocalDateTime?): String? {
        return value?.format(LOCAL_DATE_TIME_FORMATTER)
    }

    @FromJson
    fun toLocalTime(value: String?): LocalTime? {
        return if (value.isNullOrEmpty())
            null else LocalTime.parse(value, LOCAL_TIME_FORMATTER)
    }

    @ToJson
    fun fromLocalTime(value: LocalTime?): String? {
        return value?.format(LOCAL_TIME_FORMATTER)
    }

    @FromJson
    fun toLocalDate(value: String?): LocalDate? {
        return if (value.isNullOrEmpty())
            null else LocalDate.parse(value, LOCAL_DATE_FORMATTER)
    }

    @ToJson
    fun fromLocalDate(value: LocalDate?): String? {
        return value?.format(LOCAL_DATE_FORMATTER)
    }
}