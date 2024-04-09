package com.example.adabank.feature_ababank.domain.useCases.profile

import com.example.adabank.feature_ababank.domain.repository.GetProfileRepository

class GetProfileName(
    private val profileRepository: GetProfileRepository
) {

    suspend operator fun invoke(): String {
        return profileRepository().name
    }
}