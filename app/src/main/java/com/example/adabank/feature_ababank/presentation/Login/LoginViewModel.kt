package com.example.adabank.feature_ababank.presentation.Login
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.feature_ababank.domain.repository.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInRepository: SignInRepository
): ViewModel(){

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private var verificationCode = "1234"

    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.EnteredNumber -> {
                if (_loginState.value.number.length<10){
                    _loginState.value = loginState.value.copy(
                        number = _loginState.value.number+event.value,
                        nextState = false
                    )
                    if (_loginState.value.number.length==10){
                        _loginState.value = loginState.value.copy(
                            nextState = true
                        )
                    }
                }
            }
            is LoginEvent.SelectPreNumber -> {
                _loginState.value = loginState.value.copy(
                    preNumber = event.value
                )
            }
            is LoginEvent.EnteredCode -> {
                _loginState.value = loginState.value.copy(
                    code = event.value,
                    isVerificationSuccessful = event.value.equals(verificationCode)
                )
            }
            LoginEvent.DeleteNumber -> {
                _loginState.value = loginState.value.copy(
                    number = _loginState.value.number.dropLast(1),
                    nextState = false
                )
            }
            LoginEvent.OpenVerification -> {
                _loginState.value = loginState.value.copy(
                    isVerificationScreenOpen = !loginState.value.isVerificationScreenOpen
                )
            }

            LoginEvent.LogIn -> {
                viewModelScope.launch {
                    try {
                        signInRepository()
                        _loginState.value = loginState.value.copy(
                            isLoginSuccessful = true
                        )
                    }catch (e: Exception){
                        Log.i("supabaseClient", e.message.toString())
                        _loginState.value = loginState.value.copy(
                            exception = e.message.toString()
                        )
                    }

                }
            }

            LoginEvent.CloseVerification -> {
                _loginState.value = loginState.value.copy(
                    isVerificationScreenOpen = false
                )
            }
        }

    }

    fun setExceptionDefault(s: String) {
        _loginState.value = loginState.value.copy(
            exception = s
        )
    }

}