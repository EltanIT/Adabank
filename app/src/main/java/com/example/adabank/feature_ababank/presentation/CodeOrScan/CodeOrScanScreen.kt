package com.example.adabank.feature_ababank.presentation.CodeOrScan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomAuthButton
import com.example.adabank.core.presentation.components.CustomContactItem
import com.example.adabank.core.presentation.components.CustomTopAppBar
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.PrimaryColor


@Composable
fun CodeOrScanScreen(
    navController: NavController,
    viewModel: CodeOrScanViewModel = hiltViewModel()
) {

    Box(Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.ic_camerax),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)

        Image(
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)

        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Background2Color, RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))) {
            Spacer(modifier = Modifier.height(32.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .border(1.dp, PrimaryColor, RoundedCornerShape(15.dp))){
                CustomContactItem(
                    name = "Ojaman",
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


        CustomTopAppBar(
            title = "",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)) {
            navController.popBackStack()
        }
    }
}