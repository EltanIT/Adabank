package com.example.adabank.feature_ababank.presentation.CodeQr

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomAuthButton
import com.example.adabank.core.presentation.components.CustomProfileImage
import com.example.adabank.core.presentation.components.CustomTopAppBar
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.PrimaryColor


@Composable
fun CodeQrScreen(
    navController: NavController,
    viewModel: CodeQrViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(
        Modifier
            .fillMaxSize()
            .background(Background2Color)) {
        CustomTopAppBar(title = "", color = Color.Black) {
            navController.popBackStack()
        }
        
        Column(Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "My Qr Code",
                style = MaterialTheme.typography.titleSmall.copy()
            )
            Column(
                Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.White, RoundedCornerShape(30.dp)),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Row(Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                    verticalAlignment = Alignment.CenterVertically) {

                    CustomProfileImage(
                        image = state.image,
                        color = PrimaryColor
                    )
                    Spacer(modifier = Modifier.width(28.dp))
                    Column() {
                        Text(text = "Profile Name",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            lineHeight = 14.sp,
                            color = Color.Black.copy(0.6f)
                        ))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = state.name,
                            style = MaterialTheme.typography.titleLarge.copy(
                                lineHeight = 24.sp
                            ))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_line),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(text = "Scan This Code To Pay",
                    style = MaterialTheme.typography.bodySmall.copy())

                Spacer(modifier = Modifier.height(24.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_qr_code),
                    contentDescription = null,
                    modifier = Modifier.size(188.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(36.dp))
                
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.ic_swap),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Change To Barcode",
                        style = MaterialTheme.typography.bodyMedium.copy())
                }
                Spacer(modifier = Modifier.height(33.dp))
            }

            CustomAuthButton(
                text = "SCAN CODE QR",
                state = true,
                modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                navController.navigate(Route.CodeOrScan.route)
            }
        }
    }
}