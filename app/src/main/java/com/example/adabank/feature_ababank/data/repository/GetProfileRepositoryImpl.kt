package com.example.adabank.feature_ababank.data.repository

import android.util.Log
import com.example.adabank.feature_ababank.data.manger.ProfileManger
import com.example.adabank.feature_ababank.data.network.SupabaseInit.client
import com.example.adabank.feature_ababank.domain.model.ProfileData
import com.example.adabank.feature_ababank.domain.repository.GetProfileRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class GetProfileRepositoryImpl(
    private val profileManger: ProfileManger
): GetProfileRepository {
    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun invoke(): ProfileData {
        val profileCach = profileManger.getProfile()
        if (profileCach == null){
            val id = client.auth.currentUserOrNull()?.id?:""
            Log.i("supabaseClient", id)
            val data = client.postgrest["profile"].select {
                filter {
                    eq("user_id", id)
                }
                single()
            }.data

            val profile = Json {
                ignoreUnknownKeys = true
            }.decodeFromString<ProfileData>(data)

            profileManger.saveProfile(profile)

            return profile
        }

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val id = client.auth.currentUserOrNull()?.id?:""
                Log.i("supabaseClient", id)
                val data = client.postgrest["profile"].select {
                    filter {
                        eq("user_id", id)
                    }
                    single()
                }.data

                val profile = Json {
                    ignoreUnknownKeys = true
                }.decodeFromString<ProfileData>(data)

                profileManger.saveProfile(profile)
            }catch (_: Exception){}
        }

        return profileCach
    }
}