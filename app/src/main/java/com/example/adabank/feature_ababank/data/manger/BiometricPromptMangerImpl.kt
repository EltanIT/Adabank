package com.example.adabank.feature_ababank.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.adabank.feature_ababank.data.utils.Constants
import com.example.adabank.feature_ababank.domain.manger.BiometricPromptManger
import kotlinx.coroutines.flow.first

class BiometricPromptMangerImpl(
    private val context: Context
): BiometricPromptManger {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(Constants.BIOMETRIC_PROMPT)

    override suspend fun saveState(state: Boolean) {
        context.datastore.edit {
            it[Constants.PreferencesKeys.BIOMETRIC_PROMPT] = state
        }
    }

    override suspend fun getState(): Boolean? {
       return context.datastore.data.first()[Constants.PreferencesKeys.BIOMETRIC_PROMPT]
    }
}