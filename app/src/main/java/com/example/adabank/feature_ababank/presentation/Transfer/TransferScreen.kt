package com.example.adabank.feature_ababank.presentation.Transfer

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomContactItem
import com.example.adabank.core.presentation.components.CustomRadioButton
import com.example.adabank.core.presentation.components.CustomTopAppBar
import com.example.adabank.core.presentation.components.SearchTextField
import com.example.adabank.feature_ababank.data.utils.Util.getActivity
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.BackgroundColor
import com.example.adabank.ui.theme.PrimaryColor
import com.google.gson.Gson


@Composable
fun TransferScreen(
    navController: NavController,
    viewModel: TransferViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val activity = LocalContext.current as AppCompatActivity?


    var isSelfPermission by remember{
        mutableStateOf(activity
            ?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.READ_CONTACTS) }
                == PackageManager.PERMISSION_GRANTED)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            isSelfPermission = granted
        }
    )


    LaunchedEffect(key1 = !isSelfPermission) {
        if (isSelfPermission){
            viewModel.onEvent(TransferEvent.GetAllContacts)
        }
    }


    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.READ_CONTACTS)
    }



    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)) {
        CustomTopAppBar(title = "Transfer") {
            navController.popBackStack()
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Spacer(modifier = Modifier.height(48.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 52.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    repeat(state.transferTypeList.size){
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CustomRadioButton(
                                isSelected = 0==it,
                                modifier = Modifier.clickable {
                                    when(it){
                                        1 -> {navController.navigate(Route.CodeOrScan.route)}
                                        2 -> {navController.navigate(Route.Nearby.route)}
                                    }
                                })
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = state.transferTypeList[it],
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight(500),
                                    color = Color.White.copy(
                                        alpha = if (0==it) 1f else 0.5f
                                    )
                                ))

                        }
                    }
                }
                Spacer(modifier = Modifier.height(45.dp))
            }
            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            Background2Color, RoundedCornerShape(
                                topEnd = 30.dp, topStart = 30.dp
                            )
                        )
                ) {
                    Spacer(modifier = Modifier.height(28.dp))
                    SearchTextField(value = state.search,
                        hilt = "Search Contact",
                        modifier = Modifier.padding(horizontal = 24.dp),
                        onValueChangeListener = {viewModel.onEvent(TransferEvent.EnteredSearch(it))}) {
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "Recents Contacts",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black.copy(alpha = 0.5f),
                            fontWeight = FontWeight(500)
                        ),
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    LazyRow(
                        contentPadding = PaddingValues()
                    ){
                        items(state.recentsContacts.size){index ->
                            if (index == 0){
                                Spacer(modifier = Modifier.width(6.dp))
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Box( modifier = Modifier
                                .border(
                                    1.dp,
                                    if (state.selectContact == state.recentsContacts[index].number) PrimaryColor else Color.Transparent,
                                    RoundedCornerShape(15.dp)
                                )
                                .clickable {
                                    if (state.selectContact == state.recentsContacts[index].number) {
                                        val json = Gson().toJson(state.recentsContacts[index])
                                        Log.i("supabaseClient", json)
                                        navController.navigate(
                                            Route.TransferDetail.route.replace(
                                                "{contact}",
                                                json
                                            )
                                        )
                                    } else {
                                        viewModel.onEvent(TransferEvent.SelectContact(state.recentsContacts[index].number))
                                    }

                                }){
                                Column(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(
                                            ImageRequest
                                                .Builder(LocalContext.current)
                                                .data(data = state.recentsContacts[index].image)
                                                .build()
                                        ),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(50.dp)
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(Color.Black.copy(alpha = 0.4f))
                                    )
                                    Spacer(modifier = Modifier.height(24.dp))
                                    Text(
                                        text = state.recentsContacts[index].name,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            fontWeight = FontWeight(500),
                                            lineHeight = 16.sp
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Bank - "+state.recentsContacts[index].bank,
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            fontWeight = FontWeight(400),
                                            lineHeight = 12.sp,
                                            color = Color.Black.copy(alpha = 0.5f)

                                        )
                                    )
                                }
                            }
                            if (index == state.recentsContacts.size-1){
                                Spacer(modifier = Modifier.width(12.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(1.dp)
                        .background(Color.Black.copy(alpha = 0.1f)))

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(text = "All Contacts",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black.copy(alpha = 0.5f),
                            fontWeight = FontWeight(500)
                        ),
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                }
            }
            itemsIndexed(state.allContacts){index, contact  ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(Background2Color)
                    .padding(bottom = 32.dp),
                    contentAlignment = Alignment.Center){
                    CustomContactItem(
                        name = contact.name,
                        bank = contact.bank,
                        image = contact.image?:"",
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .background(Background2Color)
                            .clickable {
                                val json = Gson().toJson(contact)
                                Log.i("supabaseClient", json)
                                navController.navigate(
                                    Route.TransferDetail.route.replace(
                                        "{contact}",
                                        json
                                    )
                                )
                            },
                        color = Color.Black
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                2.dp
            ),
            shape = RoundedCornerShape(0.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_btn_add),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {  }

                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = "Add Contact",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }
    }
}