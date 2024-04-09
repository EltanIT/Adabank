package com.example.adabank.feature_ababank.data.network

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseInit {

    val client = createSupabaseClient(
        supabaseUrl =  "https://grecmbtxspelsaonmzmj.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImdyZWNtYnR4c3BlbHNhb25tem1qIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTE2Mzk4MjQsImV4cCI6MjAyNzIxNTgyNH0.uFhaLP49rvcAusVfFX6M9sqES7cwjLCzyBfl6-8LCDc"
    ){
        install(Auth)
        install(Postgrest)
    }

}