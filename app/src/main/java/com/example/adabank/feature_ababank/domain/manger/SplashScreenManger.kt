package com.example.adabank.feature_ababank.domain.manger

interface SplashScreenManger {

    suspend fun save(state: Boolean)

    suspend fun get(): Boolean?
}