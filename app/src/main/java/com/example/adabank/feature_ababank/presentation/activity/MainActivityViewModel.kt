package com.example.adabank.feature_ababank.presentation.activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.feature_ababank.data.network.SupabaseInit.client
import com.example.adabank.feature_ababank.domain.useCases.SplashScreenUseCase
import com.example.adabank.feature_ababank.domain.useCases.biometric.BiometricUseCase
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val splashScreenUseCase: SplashScreenUseCase,
    private val biometricUseCase: BiometricUseCase,
): ViewModel() {

    private val _startGraphDestination = mutableStateOf("")
    val startGraphDestination: State<String> = _startGraphDestination

    private val _startDestination = mutableStateOf("")
    val startDestination: State<String> = _startDestination


    init {
        checkSplashScreen()
    }

    private fun checkSplashScreen() {
        viewModelScope.launch {
            val state = splashScreenUseCase.readSplashScreen()
            if (state == false){
                var isChecking = true
                while (isChecking){
                    when(client.auth.sessionStatus.value){
                        is SessionStatus.Authenticated ->  {
                            _startDestination.value = Route.PinCodeAuth.route
                            _startGraphDestination.value = Route.AuthGraph.route
                            isChecking = false
                        }
                        SessionStatus.LoadingFromStorage -> {

                        }
                        SessionStatus.NetworkError -> {
                            _startDestination.value = Route.Login.route
                            _startGraphDestination.value = Route.AuthGraph.route
                        }
                        is SessionStatus.NotAuthenticated -> {
                            _startDestination.value = Route.Login.route
                            _startGraphDestination.value = Route.AuthGraph.route
                            isChecking = false
                        }
                    }
                }
            }else{
                splashScreenUseCase.saveSplashScreen(false)
                _startDestination.value = Route.SplashScreen.route
                _startGraphDestination.value = Route.AuthGraph.route
            }
        }
    }
}