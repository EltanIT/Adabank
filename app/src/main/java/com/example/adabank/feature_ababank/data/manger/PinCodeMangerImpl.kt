package com.example.adabank.feature_ababank.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.adabank.feature_ababank.data.utils.Constants
import com.example.adabank.feature_ababank.domain.manger.PinCodeManger
import kotlinx.coroutines.flow.first

class PinCodeMangerImpl(
    private val context: Context
): PinCodeManger {


    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(Constants.PIN_CODE)

    override suspend fun save(code: String) {
        context.datastore.edit {
            it[Constants.PreferencesKeys.PIN_CODE] = code
        }
    }

    override suspend fun get(): String? {
        return context.datastore.data.first()[Constants.PreferencesKeys.PIN_CODE]
    }

}