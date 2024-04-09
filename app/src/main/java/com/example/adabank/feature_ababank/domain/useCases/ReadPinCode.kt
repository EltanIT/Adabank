package com.example.adabank.feature_ababank.domain.useCases

import com.example.adabank.feature_ababank.domain.manger.PinCodeManger

class ReadPinCode(
    private val pinCodeManger: PinCodeManger
) {


    suspend operator fun invoke(): String?{
        return pinCodeManger.get()
    }
}