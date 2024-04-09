package com.example.adabank.feature_ababank.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.adabank.feature_ababank.data.util.Constants
import com.example.adabank.feature_ababank.domain.manger.PinCodeManger
import com.example.adabank.feature_ababank.domain.manger.SplashScreenManger
import kotlinx.coroutines.flow.first

class SplashScreenMangerImpl(
    private val context: Context
): SplashScreenManger {


    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(Constants.SPLASH_SCREEN)


    override suspend fun save(state: Boolean) {
        context.datastore.edit {
            it[Constants.PreferencesKeys.SPLASH_SCREEN] = state
        }
    }

    override suspend fun get(): Boolean? {
        return context.datastore.data.first()[Constants.PreferencesKeys.SPLASH_SCREEN]
    }

}