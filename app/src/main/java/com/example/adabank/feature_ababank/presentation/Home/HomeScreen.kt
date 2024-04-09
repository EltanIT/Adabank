@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.adabank.feature_ababank.presentation.Home

import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomProfileImage
import com.example.adabank.feature_ababank.data.util.Util.getActivity
import com.example.adabank.feature_ababank.domain.model.TransactionData
import com.example.adabank.core.presentation.components.TransactionCategoryItem
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.Background2Color
import com.google.gson.Gson


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val activity = LocalContext.current.getActivity()

    if (activity
            ?.let { ContextCompat.checkSelfPermission(it, android.Manifest.permission.READ_CONTACTS) }
        != PackageManager.PERMISSION_GRANTED){
        activity?.let { ActivityCompat.requestPermissions(it, arrayOf(android.Manifest.permission.READ_CONTACTS), 0) }
    }


    val state = viewModel.state.value

    val actionList = viewModel.actionList

    var transferNav = remember{
        mutableStateOf(false)
    }

    var columnHeightDb = remember {
        mutableStateOf(0.dp)
    }

    LaunchedEffect(!transferNav.value){
        if(transferNav.value){
            navController.navigate(Route.Transfer.route)
        }
    }

    val scaffoldState = rememberBottomSheetScaffoldState()

    val density = LocalDensity.current
    Column(
        Modifier
            .fillMaxSize()
            .onGloballyPositioned {
                columnHeightDb.value = with(density) { it.size.height.toDp() }
            }
            .padding(top = 28.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(text = "Welcome",
                style = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 14.sp,
                    color = Color.White
                ),
                modifier = Modifier.alpha(0.6f))
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = state.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        lineHeight = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight(500)
                    )
                )
            }
           CustomProfileImage(image = state.image){
               navController.navigate(Route.Profile.route)
           }

        }
        Spacer(modifier = Modifier.height(33.dp))
        
        Box(Modifier.fillMaxSize()) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.15f)
                ),
                elevation = CardDefaults.cardElevation(
                    0.dp
                ),
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(2.dp, Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.5f),
                        Color.White.copy(alpha = 0.05f)
                    )
                ))
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(Modifier.fillMaxWidth()) {
                        Text(text = "Total Balance",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight(400),
                                color = Color.White.copy(alpha = 0.6f)
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                        Image(painter = painterResource(id = R.drawable.ic_eye_show), contentDescription = null,
                            Modifier
                                .align(Alignment.CenterEnd)
                                .clickable {

                                })
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "$"+state.balance,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 44.sp,
                            lineHeight = 66.sp,
                            color = Color.White
                        ))
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(modifier = Modifier.background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(10.dp))){
                        Row(Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
                            Text(text = "USD",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    color = Color.White
                                ))
                            Image(painter = painterResource(id = R.drawable.ic_arrow_down_2), contentDescription = null)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.3f)))
                    Spacer(modifier = Modifier.height(20.dp))


                    Row(Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        repeat(actionList.size){
                            Column(horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .clickable {
                                        when(it){
                                            0 -> {
//                                    navController.navigate(Route.Transfer.route)
                                                transferNav.value = true
                                            }
                                            1 -> {navController.navigate(Route.TopUpWallet.route)}
                                            2 -> {}
                                            3 -> {navController.navigate(Route.Menu.route)}
                                        }
                                    }) {
                                Image(painter = painterResource(id = actionList[it].image), contentDescription = null)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = actionList[it].title,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight(400)
                                    ))
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
            BottomSheetScaffold(
                modifier = Modifier.padding(top = 1.dp),
                scaffoldState = scaffoldState,
                sheetDragHandle = {
                    Spacer(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .height(1.dp)
                            .width(20.dp)
                            .background(Color.Black.copy(alpha = 0.1f))
                    )
                },
                sheetContent = {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, start = 24.dp, end = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Send Again",
                            Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight(500),
                                color = Color.Black.copy(alpha = 0.5f),
                                textAlign = TextAlign.Start
                            ))
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(state.sendHistory.size){
                                Column(horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(15.dp))
                                        .clickable {
                                            val json = Gson().toJson(state.sendHistory[it])
                                            Log.i("supabaseClient", json)
                                            navController.navigate(
                                                Route.TransferDetail.route.replace(
                                                    "{contact}",
                                                    json
                                                )
                                            )
                                        }) {
                                    Image(
                                        painter = rememberAsyncImagePainter(model = state.sendHistory[it].image),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(56.dp)
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(
                                                Color.Black.copy(alpha = 0.3f),
                                                RoundedCornerShape(15.dp)
                                            ),
                                        contentScale = ContentScale.Crop)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(text = state.sendHistory[it].name,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.width(56.dp),
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color.Black,
                                            fontWeight = FontWeight(400)
                                        ))
                                }
                            }
                        }


                        Spacer(modifier = Modifier.height(28.dp))
                        Text(text = "Transaction History",
                            Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight(500),
                                color = Color.Black.copy(alpha = 0.5f),
                                textAlign = TextAlign.Start
                            ))

                        Spacer(modifier = Modifier.height(16.dp))

                        LazyColumn(){
                            items(
                                count = state.transactionHistory.size,
                                key = {state.transactionHistory[it].id?:-1}
                            ){
                                if (it!=0){
                                    Spacer(modifier = Modifier.height(20.dp))
                                }
                                TransactionCategoryItem(
                                    transactionData = TransactionData(
                                        "Equipment",
                                        "Laptop Acer aspire 5",
                                        R.drawable.ic_category_equipment,
                                        state.transactionHistory[it].total,
                                        state.transactionHistory[it].created_at,
                                        state.transactionHistory[it].transaction_id
                                    ),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .clickable { navController.navigate(Route.GraphDetails.route) }
                                )
                                if (it == state.transactionHistory.size-1){
                                    Spacer(modifier = Modifier.height(110.dp))
                                }
                            }
                        }
                    }
                },
                sheetPeekHeight = 354.dp,
                sheetContainerColor = Background2Color
            ) {

            }
        }




    }

}