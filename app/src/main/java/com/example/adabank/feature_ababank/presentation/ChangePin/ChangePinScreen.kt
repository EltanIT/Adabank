package com.example.adabank.feature_ababank.presentation.ChangePin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adabank.core.presentation.components.CardTop
import com.example.adabank.core.presentation.components.CustomAuthButton
import com.example.adabank.feature_ababank.presentation.ChangePin.components.CustomTextField
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.BackgroundColor


@Composable
fun ChangePinScreen(
    navController: NavController,
    viewModel: ChangePinViewModel
) {


    val state = viewModel.state.value
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)) {
        CardTop(
            title = "Change PIN",
            balance = state.balance,
            card = "5282 3456 7890 1289",
            modifier = Modifier)
        Column(
            Modifier
                .fillMaxSize()
                .background(Background2Color)) {
            Spacer(modifier = Modifier.height(92.dp))
            Column(
                Modifier
                    .padding(horizontal = 24.dp)
                    .weight(1f)) {
                CustomTextField(value = state.newPin,
                    hilt = "New PIN",
                    isVisible = state.isVisibleNewPin,
                    onValueChangeListener = {viewModel.onEvent(ChangePinEvent.EnteredNewPin(it))}) {
                    viewModel.onEvent(ChangePinEvent.ChangeVisibleNewPin)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Enter 6 numbers as new PIN to retain your card",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight(400),
                        lineHeight = 15.sp,
                        color = Color.Black.copy(alpha = 0.3f)
                    ),)
                Spacer(modifier = Modifier.height(46.dp))
                CustomTextField(value = state.confirmNewPin,
                    hilt = "Confirmation New PIN",
                    isVisible = state.isVisibleConfirmNewPin,
                    onValueChangeListener = {viewModel.onEvent(ChangePinEvent.EnteredConfirmNewPin(it))}) {
                    viewModel.onEvent(ChangePinEvent.ChangeVisibleConfirmNewPin)
                }
            }
            CustomAuthButton(text = "SAVE", state = true, modifier = Modifier.padding(horizontal = 24.dp)) {
                if (state.isSame){
                    viewModel.onEvent(ChangePinEvent.SaveNewPinCode)
                    Toast.makeText(context, "Новый пин код сохранен", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Поля ввода не совпадают", Toast.LENGTH_SHORT).show()
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}