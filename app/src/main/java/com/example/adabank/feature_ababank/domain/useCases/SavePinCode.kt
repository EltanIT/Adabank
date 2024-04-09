package com.example.adabank.feature_ababank.domain.useCases

import com.example.adabank.feature_ababank.domain.manger.PinCodeManger

class SavePinCode(
    private val pinCodeManger: PinCodeManger
) {


    suspend operator fun invoke(code: String){
        pinCodeManger.save(code)
    }
}