package com.example.adabank.core.presentation.components

import android.content.Intent
import android.hardware.biometrics.BiometricManager
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.adabank.feature_ababank.presentation.Fingerprint.BiometricPromptManager


@Composable
fun BiometricPrompt(
    title: String = "",
    description: String = "",
    isOpen: Boolean,
    onGetException: (String) -> Unit,
    onCancel: () -> Unit,
    onSuccess: () -> Unit,
) {

    val activity = LocalContext.current
    val promptManager by remember{
        lazy {
            BiometricPromptManager(activity as AppCompatActivity)
        }
    }

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
                        BiometricManager.Authenticators.BIOMETRIC_STRONG
                                or BiometricManager.Authenticators.DEVICE_CREDENTIAL
                    )
                }
                enrollLauncher.launch(enrollIntent)
            }
        }
    }

    LaunchedEffect(key1 = !isOpen) {
        if (isOpen){
            promptManager.showBiometricPrompt(
                title = title,
                description = description
            )
        }
    }


    biometricResult?.let { result ->
        when(result) {
            is BiometricPromptManager.BiometricResult.AuthenticationError -> {
                onCancel()
            }
            BiometricPromptManager.BiometricResult.AuthenticationFailed -> {
                onGetException("Authentication failed")
            }
            BiometricPromptManager.BiometricResult.AuthenticationNotSet -> {
                onGetException("Authentication not set")
            }
            BiometricPromptManager.BiometricResult.AuthenticationSuccess -> {
                onSuccess()
            }
            BiometricPromptManager.BiometricResult.FeatureUnavailable -> {
                onGetException("Feature unavailable")
            }
            BiometricPromptManager.BiometricResult.HardwareUnavailable -> {
                onGetException("Hardware unavailable")
            }
        }
    }
}