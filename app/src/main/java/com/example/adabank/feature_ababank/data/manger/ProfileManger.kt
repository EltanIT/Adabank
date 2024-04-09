package com.example.adabank.feature_ababank.data.manger

import com.example.adabank.feature_ababank.domain.model.ProfileData

interface ProfileManger {

    suspend fun getProfile(): ProfileData?
    suspend fun saveProfile(profileData: ProfileData)
}