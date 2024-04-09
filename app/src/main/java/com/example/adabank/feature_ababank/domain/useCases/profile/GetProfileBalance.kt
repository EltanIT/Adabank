package com.example.adabank.feature_ababank.domain.useCases.profile

import com.example.adabank.feature_ababank.domain.repository.GetProfileRepository

class GetProfileBalance(
    private val profileRepository: GetProfileRepository
) {

    suspend operator fun invoke(): String {
        return profileRepository().balance
    }
}