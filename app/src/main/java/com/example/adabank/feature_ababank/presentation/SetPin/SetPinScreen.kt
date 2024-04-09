package com.example.adabank.feature_ababank.presentation.SetPin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomAuthButton
import com.example.adabank.core.presentation.components.CustomInputNumber
import com.example.adabank.core.presentation.components.PinCodeCompose
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.PrimaryColor

@Composable
fun SetPinScreen(
    navController: NavController,
    viewModel: SetPinViewModel = hiltViewModel()
) {

    val state = viewModel.setPinState.value

    Column(
        Modifier
            .fillMaxSize()
            .background(Background2Color)
            .padding(horizontal = 24.dp)) {
        Spacer(modifier = Modifier.height(30.dp))
        Image(painter = painterResource(id = R.drawable.ic_back), contentDescription = null,
        Modifier.clickable { navController.popBackStack() })
        Spacer(modifier = Modifier.height(60.dp))

        Column(Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween) {
            Column(Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Set your PIN",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 32.sp,
                        lineHeight = 48.sp
                    ))
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "You will get use this to login next time",
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
            Column() {
                CustomInputNumber(
                    onValueChangeListener = {viewModel.onEvent(SetPinEvent.EnteredCode(it))},
                    onValueDeleteListener = {viewModel.onEvent(SetPinEvent.DeleteCode)},
                    modifier = Modifier.padding(horizontal = 40.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomAuthButton(
                    text = "NEXT",
                    state = state.stateNext) {
                    viewModel.onEvent(SetPinEvent.SavePinCode)
                    navController.navigate(Route.FingerPrint.route)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }


        }



    }
}