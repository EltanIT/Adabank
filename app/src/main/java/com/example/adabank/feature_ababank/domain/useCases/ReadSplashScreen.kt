package com.example.adabank.feature_ababank.domain.useCases

import com.example.adabank.feature_ababank.domain.manger.SplashScreenManger

class ReadSplashScreen(
    private val splashScreenManger: SplashScreenManger
) {


    suspend operator fun invoke(): Boolean? {
        return splashScreenManger.get()
    }
}