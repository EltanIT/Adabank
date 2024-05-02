package com.example.adabank.feature_ababank.presentation.PinCodeAuth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.core.presentation.components.BiometricPrompt
import com.example.adabank.core.presentation.components.CustomInputNumber
import com.example.adabank.core.presentation.components.PinCodeCompose
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.Background2Color


@Composable
fun PinCodeAuthScreen(
    navController: NavController,
    viewModel: PinCodeAuthViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    LaunchedEffect(key1 = !state.successful) {
        if (state.successful){
            navController.navigate(Route.NavigationHome.route)
        }
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = state.exception) {
        if (state.exception.isNotEmpty()){
            viewModel.onEvent(PinCodeAuthEvent.SetException(""))
//            Toast.makeText(context, state.exception, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Background2Color)
            .padding(start = 24.dp, end = 24.dp, top = 90.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Entered your PIN",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 32.sp,
                    lineHeight = 48.sp
                ))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Get use this to login now",
                modifier = Modifier.alpha(0.5f),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                ))
            Spacer(modifier = Modifier.height(56.dp))
            PinCodeCompose(
                Modifier.padding(horizontal = 15.dp),
                value = state.code,
                visualTransformation = PasswordVisualTransformation('*'),
                onValueChangeListener = {})
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CustomInputNumber(
                onValueChangeListener = {viewModel.onEvent(PinCodeAuthEvent.EnteredCode(it))},
                onValueDeleteListener = {viewModel.onEvent(PinCodeAuthEvent.DeleteCode)},
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            if (state.fingerprintIsSupport){
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "open fingerprint",
                    modifier = Modifier.clickable {
                        viewModel.onEvent(PinCodeAuthEvent.OpenFingerprint)
                    }
                )
            }

        }
        
    }

    BiometricPrompt(
        title = "Войдите с помощью отпечатка пальцев",
        isOpen = state.fingerprintIsOpen,
        onGetException = {viewModel.onEvent(PinCodeAuthEvent.SetException(it))},
        onCancel = {},
        onSuccess = {viewModel.onEvent(PinCodeAuthEvent.Success)}
    )
}