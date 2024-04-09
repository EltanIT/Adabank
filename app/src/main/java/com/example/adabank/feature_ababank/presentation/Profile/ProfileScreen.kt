@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.adabank.feature_ababank.presentation.Profile

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomProfileImage
import com.example.adabank.core.presentation.components.CustomSwitch
import com.example.adabank.feature_ababank.presentation.Profile.components.ProfileDetailsScreen
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.BackgroundColor


@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    val sheetState = rememberModalBottomSheetState(true)
    if (state.isProfileDetailsOpen){
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {viewModel.onEvent(ProfileEvent.OpenProfileDetails)},
            dragHandle = {},
            containerColor = Background2Color
        ) {
            ProfileDetailsScreen(
                profile = state.profile,
                primaryDevise = state.primaryDevice,
                appVersion = "3.0.2(7722)"
            )
        }
    }


    Box(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_profile_ellipse),
            contentDescription = null,
            modifier =Modifier.align(Alignment.TopEnd)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_profile_ellipse_2),
            contentDescription = null,
            modifier =Modifier.align(Alignment.TopStart)
        )

        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                 verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Profile", style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight(500)
                ))
                IconButton(onClick = {navController.navigate(Route.ProfileSettings.route)}) {
                    Image(painter = painterResource(id = R.drawable.ic_setting), contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            CustomProfileImage(image = state.profile.image)

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = state.profile.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight(500),
                    lineHeight = 20.sp,
                    color = Color.White
                ))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = state.profile.email,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight(400),
                    lineHeight = 14.sp,
                    color = Color.White.copy(alpha = 0.6f)
                )
            )

            Spacer(modifier = Modifier.height(36.dp))

            Column(
                Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .background(Background2Color, RoundedCornerShape(20.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Personal Account Information",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight(500)
                    ))
                Spacer(modifier = Modifier.height(19.dp))
                Spacer(modifier = Modifier
                    .padding(horizontal = 27.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Black.copy(alpha = 0.1f))
                )
                Spacer(modifier = Modifier.height(13.dp))

                Row(
                    Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = "$" + "Castag",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight(400),
                            color = Color.Black.copy(alpha = 0.5f),
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    Text(text = "$"+state.profile.castag,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight(500)
                        )
                    )
                    Spacer(modifier = Modifier.width(9.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_copy),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            clipboardManager.setText(AnnotatedString(state.profile.castag))
                            Toast.makeText(context, "Castag is copied", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = "Account number",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight(400),
                            color = Color.Black.copy(alpha = 0.5f),
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    Text(text = state.profile.accountNumber,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight(500),
                        )
                    )
                    Spacer(modifier = Modifier.width(9.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_copy),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            clipboardManager.setText(AnnotatedString(state.profile.accountNumber))
                            Toast.makeText(context, "Account number is copied", Toast.LENGTH_SHORT).show()
                        }
                    )
                }

                IconButton(onClick = {viewModel.onEvent(ProfileEvent.OpenProfileDetails)}) {
                    Image(painter = painterResource(id = R.drawable.ic_arrow_down), contentDescription = null)
                }

            }

            Spacer(modifier = Modifier.height(36.dp))

            Column(
                Modifier
                    .background(
                        Background2Color,
                        RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )
                    .fillMaxWidth()
                    .weight(1f)) {
                Spacer(modifier = Modifier.height(36.dp))
                Text(text = "General",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight(400),
                        color = Color.Black.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column() {
                    repeat(viewModel.listGeneral.size){
                        GeneralSettingItem(
                            icon = viewModel.listGeneral[it].icon,
                            title = viewModel.listGeneral[it].title,
                            description = viewModel.listGeneral[it].description,
                            isEnable = when (it) {
                                0 -> state.onlinePayment
                                1 -> state.atmWindrawals
                                2 -> state.paymentAbroad
                                else -> false
                            },
                            onSwitchClickListener = {
                                when(it){
                                    0 -> {viewModel.onEvent(ProfileEvent.OnlinePaymentClick)}
                                    1 -> {viewModel.onEvent(ProfileEvent.AtmWindrawalsClick)}
                                    2 -> {viewModel.onEvent(ProfileEvent.PaymentAbroadClick)}
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Help Support",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight(400),
                        color = Color.Black.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun GeneralSettingItem(
    @DrawableRes icon: Int,
    title: String,
    description: String,
    isEnable: Boolean,
    onSwitchClickListener: () -> Unit,
) {
    
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Image(
            painter = painterResource(id = icon), 
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight(500)
            ))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = description,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight(400),
                    color = Color.Black.copy(0.5f)
                ))
        }
        CustomSwitch(isCheck = isEnable) {
            onSwitchClickListener()
        }
    }
}