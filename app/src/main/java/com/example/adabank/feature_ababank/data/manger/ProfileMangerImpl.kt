package com.example.adabank.feature_ababank.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.adabank.feature_ababank.data.utils.Constants
import com.example.adabank.feature_ababank.domain.model.ProfileData
import com.google.gson.Gson
import kotlinx.coroutines.flow.first

class ProfileMangerImpl(
    private val context: Context
): ProfileManger {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(Constants.PROFILE)

    override suspend fun getProfile(): ProfileData? {
        val profileJson = context.datastore.data.first()[Constants.PreferencesKeys.PROFILE]
        profileJson?.let {
            return Gson().fromJson(profileJson, ProfileData::class.java)
        }
        return null
    }

    override suspend fun saveProfile(profileData: ProfileData) {
        context.datastore.edit {
            it[Constants.PreferencesKeys.PROFILE] = Gson().toJson(profileData)
        }
    }
}