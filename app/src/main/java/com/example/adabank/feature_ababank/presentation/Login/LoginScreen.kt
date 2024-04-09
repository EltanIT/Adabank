@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.adabank.feature_ababank.presentation.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomAlertDialog
import com.example.adabank.core.presentation.components.CustomAuthButton
import com.example.adabank.core.presentation.components.CustomInputNumber
import com.example.adabank.feature_ababank.presentation.Login.components.CustomTextField
import com.example.adabank.feature_ababank.presentation.Login.components.VerificationScreen
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.Background2Color

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state = viewModel.loginState.value
    val sheetState = rememberModalBottomSheetState()


    LaunchedEffect(!state.isVerificationSuccessful){
        if (state.isVerificationSuccessful){
            viewModel.onEvent(LoginEvent.OpenVerification)
            viewModel.onEvent(LoginEvent.LogIn)
        }
    }

    LaunchedEffect(!state.isLoginSuccessful){
        if (state.isLoginSuccessful){
            navController.navigate(Route.SetPin.route)
        }
    }

    if (state.isVerificationScreenOpen){
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {viewModel.onEvent(LoginEvent.CloseVerification)},
            containerColor = Background2Color,
            scrimColor = Color.Black.copy(0.5f),
            dragHandle = {

            }
        ) {
            VerificationScreen(
                number = state.preNumber + state.number,
                code = state.code,
                timer = "Resend Code in 00:48",
                onCodeChangeListener = {viewModel.onEvent(LoginEvent.EnteredCode(it))}
            )
        }
    }

    if (state.exception.isNotEmpty()) {
        CustomAlertDialog(exception = state.exception) {
            viewModel.setExceptionDefault("")
        }
    }

    
    Column(
        Modifier
            .fillMaxSize()
            .background(Background2Color),
    verticalArrangement = Arrangement.SpaceBetween) {
        Column(Modifier
            .padding(horizontal = 24.dp)
        ){
            Spacer(modifier = Modifier.height(60.dp))
            Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = null,)
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Welcome",
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 32.sp,
                lineHeight = 48.sp
            ))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Enter your mobile number  for Continue",
                modifier = Modifier.alpha(0.5f),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                ))
            Spacer(modifier = Modifier.height(48.dp))


            CustomTextField(
                value = state.number,
                preNumbers = listOf(""),
                selectedPreNumber = state.preNumber
            )
        }

        Column(Modifier
            .padding(horizontal = 24.dp)) {
            CustomInputNumber(
                onValueChangeListener = {viewModel.onEvent(LoginEvent.EnteredNumber(it))},
                onValueDeleteListener = {viewModel.onEvent(LoginEvent.DeleteNumber)},
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            CustomAuthButton(
                text = "NEXT",
                state = state.nextState) {
                viewModel.onEvent(LoginEvent.OpenVerification)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }


    }
}