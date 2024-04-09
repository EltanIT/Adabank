package com.example.adabank.feature_ababank.domain.useCases

import com.example.adabank.feature_ababank.domain.manger.SplashScreenManger

class SaveSplashScreen(
    private val splashScreenManger: SplashScreenManger
) {


    suspend operator fun invoke(state: Boolean){
        splashScreenManger.save(state)
    }
}