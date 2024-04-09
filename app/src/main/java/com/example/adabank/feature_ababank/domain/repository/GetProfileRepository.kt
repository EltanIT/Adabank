package com.example.adabank.feature_ababank.domain.repository

import com.example.adabank.feature_ababank.domain.model.ProfileData

interface GetProfileRepository {

    suspend operator fun invoke(): ProfileData
}