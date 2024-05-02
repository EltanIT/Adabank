package com.example.adabank.feature_ababank.presentation.Fingerprint

import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomAuthButton
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.Background2Color


@Composable
fun FingerPrintScreen(
    navController: NavController,
    viewModel: FingerprintViewModel = hiltViewModel()
) {
    val activity = LocalContext.current
    val promptManager by remember{
        lazy {
            BiometricPromptManager(activity as AppCompatActivity)
        }
    }

    val state = viewModel.state.value

    val biometricResult by promptManager.promptResults.collectAsState(
        initial = null
    )
    val enrollLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            println("Activity result: $it")
        }
    )

    LaunchedEffect(biometricResult) {
        if(biometricResult is BiometricPromptManager.BiometricResult.AuthenticationNotSet) {
            if(Build.VERSION.SDK_INT >= 30) {
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(
                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                    )
                }
                enrollLauncher.launch(enrollIntent)
            }
        }
    }

    LaunchedEffect(key1 = state.fingerprintPromptIsOpen) {
        if (state.fingerprintPromptIsOpen){
            promptManager.showBiometricPrompt(
                title = "Use Touch ID to\n" +
                        "authorise payments",
                description = "Active touch ID so you don’t need to confirm  \n" +
                        "your PIN every time you want to send money"
            )
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Background2Color)) {

        Spacer(modifier = Modifier.height(28.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription =null,
            Modifier
                .padding(start = 24.dp)
                .clickable { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(48.dp))
        Column(
            Modifier
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {
            Image(painter = painterResource(id = R.drawable.ic_fingerprint), contentDescription =null)
            Column() {
                Text(text = "Use Touch ID to Authorise Payments",
                style = MaterialTheme.typography.titleLarge)
                
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Active touch ID so you don’t need to confirm your PIN every time you want to send money",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight(400)
                    ))
            }

            CustomAuthButton(
                text = "FINISH",
                state = true) {
                if (state.isSuccess){
                    navController.navigate(Route.NavigationHome.route)
                }

            }

            CustomAuthButton(
                text = "SKIP",
                state = true,
            modifier = Modifier.alpha(0.5f)) {
                navController.navigate(Route.NavigationHome.route)
            }
        }

    }


    biometricResult?.let { result ->
        when(result) {
            is BiometricPromptManager.BiometricResult.AuthenticationError -> {
                viewModel.onEvent(FingerprintEvent.SetException(result.error))
            }
            BiometricPromptManager.BiometricResult.AuthenticationFailed -> {
                viewModel.onEvent(FingerprintEvent.SetException("Authentication failed"))
            }
            BiometricPromptManager.BiometricResult.AuthenticationNotSet -> {
                viewModel.onEvent(FingerprintEvent.SetException("Authentication not set"))
            }
            BiometricPromptManager.BiometricResult.AuthenticationSuccess -> {
                viewModel.onEvent(FingerprintEvent.Successful)
                navController.navigate(Route.NavigationHome.route)
            }
            BiometricPromptManager.BiometricResult.FeatureUnavailable -> {
                viewModel.onEvent(FingerprintEvent.SetException( "Feature unavailable"))
            }
            BiometricPromptManager.BiometricResult.HardwareUnavailable -> {
                viewModel.onEvent(FingerprintEvent.SetException("Hardware unavailable"))
            }
        }
    }

    LaunchedEffect(key1 = state.exception) {
        if (state.exception.isNotEmpty()){
            Toast.makeText(activity, state.exception, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(FingerprintEvent.SetException(""))
        }
    }

}