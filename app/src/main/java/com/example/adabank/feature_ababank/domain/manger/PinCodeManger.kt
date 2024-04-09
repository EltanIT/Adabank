package com.example.adabank.feature_ababank.domain.manger

interface PinCodeManger {


    suspend fun save(code: String)

    suspend fun get(): String?
}