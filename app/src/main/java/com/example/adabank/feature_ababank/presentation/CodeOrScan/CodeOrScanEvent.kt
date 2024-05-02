package com.example.adabank.feature_ababank.presentation.CodeOrScan

import androidx.compose.ui.geometry.Rect

sealed class CodeOrScanEvent {
    data class OnTargetPositioned(val value: Rect): CodeOrScanEvent()
    data class OnCodeDetected(val value: String): CodeOrScanEvent()
}