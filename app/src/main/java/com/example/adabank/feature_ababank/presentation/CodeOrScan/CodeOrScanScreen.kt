
package com.example.adabank.feature_ababank.presentation.CodeOrScan

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toAndroidRect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.core.presentation.components.CustomAuthButton
import com.example.adabank.core.presentation.components.CustomContactItem
import com.example.adabank.core.presentation.components.CustomTopAppBar
import com.example.adabank.feature_ababank.data.QrCodeAnalyzer
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.PrimaryColor


@ExperimentalGetImage
@Composable
fun CodeOrScanScreen(
    navController: NavController,
    viewModel: CodeOrScanViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )
    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }



    Box(
        Modifier
            .background(PrimaryColor.copy(0.4f))
            .fillMaxSize()
    ) {


        val targetRect by remember { derivedStateOf { state.targetRect } }
        if (hasCamPermission) {
            AndroidView(
                factory = { context ->

                    val previewView = PreviewView(context)
                    val preview = Preview.Builder().build()
                    val selector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()
                    preview.setSurfaceProvider(previewView.surfaceProvider)

                    val imageAnalysis = ImageAnalysis.Builder()
                        .setTargetResolution(
                            android.util.Size(
                                previewView.width,
                                previewView.height
                            )
                        )
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()

                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        QrCodeAnalyzer(
                            targetRect = targetRect.toAndroidRect(),
                            previewView = previewView,
                        ){ result ->
                            viewModel.onEvent(CodeOrScanEvent.OnCodeDetected(result))
                        }
                    )

                    try {
                        cameraProviderFuture.get().bindToLifecycle(
                            lifecycleOwner,
                            selector,
                            preview,
                            imageAnalysis
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    previewView
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        if (state.result.isNotEmpty()){
            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        Background2Color,
                        RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .border(1.dp, PrimaryColor, RoundedCornerShape(15.dp))){
                    CustomContactItem(
                        name = "Ojaman + ${state.result}",
                        bank = "0987 3422 8756",
                        image = "https://s3-alpha-sig.figma.com/img/2f82/5d9c/113b88aff408612b2d3c71584b223659?Expires=1713139200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=ceQwBuPLcwVEQdYjYmppG5CKbO3fGRDm2ipme08-~U1TmieJPtFDrQQquzHPE4DYGg1H7J1ZEOY7ph7UIJX9EZr7qEbLYMbW8KX3-HzjsUHEUdJlYL7c7eAgR8LeU6t8Kwlq5bcWOjMxFdG80LWkMYlBRyZ2QkUzAgseGqrzXWnYzBn-M-ZDMROLwDTFS1VkAlWQMS3b62A5IEtAwMlHYtJfnNRlUS-FVZEq8mx1DJ4agTQtENCQUEDxtJycBUgCrC8QT4Y-xJNd9~rfjpJdsUZZpW7R0J24iEaE2gLQK2URYK9p3wMWTJVbkNW2mtet9XcmRXb07-kFKIx6zQNs8w__",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 15.dp),
                        color = PrimaryColor)
                }
                Spacer(modifier = Modifier.height(24.dp))
                CustomAuthButton(
                    text = "CHECK OUT",
                    state = true,
                    modifier = Modifier.padding(horizontal = 24.dp)) {
                    navController.popBackStack()
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        var widthInPx: Float = 0f
        var heightInPx: Float = 0f
        var radiusInPx: Float

        with(LocalDensity.current){
            radiusInPx = 16.dp.toPx()
        }
        Canvas(
            modifier = Modifier
                .padding(horizontal = 49.dp)
                .align(Alignment.Center)
                .fillMaxWidth()
                .aspectRatio(1 / 1f)
                .border(2.dp, Color(0xffC4C4C4), RoundedCornerShape(16.dp))
                .onGloballyPositioned {
                    viewModel.onEvent(CodeOrScanEvent.OnTargetPositioned(it.boundsInRoot()))

                    widthInPx = it.size.width.toFloat()
                    heightInPx = it.size.height.toFloat()
                }
        ) {
            val offset = Offset(
                x = (size.width - widthInPx) / 2,
                y = (size.height - heightInPx) / 2,
            )
            val cutoutRect = Rect(offset, Size(widthInPx, heightInPx))
            // Source
            drawRoundRect(
                topLeft = cutoutRect.topLeft,
                size = cutoutRect.size,
                cornerRadius = CornerRadius(radiusInPx, radiusInPx),
                color = Color.Transparent,
                blendMode = BlendMode.Clear
            )
        }

        CustomTopAppBar(
            title = "",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)) {
            navController.popBackStack()
        }
    }
}