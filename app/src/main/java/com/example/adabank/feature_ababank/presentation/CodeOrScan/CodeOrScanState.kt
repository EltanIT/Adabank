package com.example.adabank.feature_ababank.presentation.CodeOrScan

import androidx.camera.core.CameraSelector
import androidx.compose.ui.geometry.Rect

data class CodeOrScanState(
    val loading: Boolean = false,
    val result: String = "",
    val targetRect: Rect = Rect.Zero,
    val lensFacing: Int = CameraSelector.LENS_FACING_BACK,
)