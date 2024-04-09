package com.example.adabank.feature_ababank.domain.useCases

import com.example.adabank.feature_ababank.domain.model.ProfileData
import com.example.adabank.feature_ababank.domain.repository.GetProfileRepository
import com.example.adabank.feature_ababank.domain.useCases.profile.GetProfileBalance
import com.example.adabank.feature_ababank.domain.useCases.profile.GetProfileCard
import com.example.adabank.feature_ababank.domain.useCases.profile.GetProfileCastag
import com.example.adabank.feature_ababank.domain.useCases.profile.GetProfileEmail
import com.example.adabank.feature_ababank.domain.useCases.profile.GetProfileImage
import com.example.adabank.feature_ababank.domain.useCases.profile.GetProfileName

class ProfileUseCase(
    private val getProfileRepository: GetProfileRepository,
    val getProfileName: GetProfileName,
    val getProfileCard: GetProfileCard,
    val getProfileCastag: GetProfileCastag,
    val getProfileEmail: GetProfileEmail,
    val getProfileImage: GetProfileImage,
    val getProfileBalance: GetProfileBalance,
) {

    suspend operator fun invoke(): ProfileData{
            return getProfileRepository()
    }
}