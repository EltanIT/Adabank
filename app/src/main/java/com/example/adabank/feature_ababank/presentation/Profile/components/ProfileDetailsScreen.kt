package com.example.adabank.feature_ababank.presentation.Profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.adabank.feature_ababank.domain.model.ProfileData
import com.example.adabank.ui.theme.PrimaryColor


@Composable
fun ProfileDetailsScreen(
    profile: ProfileData,
    primaryDevise: String,
    appVersion: String
) {

    Column(Modifier.fillMaxWidth()) {
        Column(Modifier
            .background(Color.White)
            .blur(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Spacer(modifier = Modifier
                .width(20.dp)
                .height(1.dp)
                .background(Color.Black.copy(0.1f)))
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Detail Profile",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight(500)
                    )
                )
                Text(
                    text = "Edit",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight(500),
                        color = PrimaryColor
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        Spacer(modifier = Modifier.height(46.dp))

        Column(Modifier.padding(horizontal = 24.dp)) {
            DetailItem(title = "Name", value = profile.name)
            Spacer(modifier = Modifier.height(16.dp))
            DetailItem(title = "$"+"Castag", value = "$"+profile.castag)
            Spacer(modifier = Modifier.height(16.dp))
            DetailItem(title = "Account number", value = profile.accountNumber)

            Spacer(modifier = Modifier.height(32.dp))
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black.copy(0.1f))
            )
            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "Personal Information",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight(500)
                )
            )
            Spacer(modifier = Modifier.height(28.dp))


            DetailItem(title = "Email", value = profile.email)
            Spacer(modifier = Modifier.height(16.dp))
            DetailItem(title = "Mobile Number", value = profile.number)
            Spacer(modifier = Modifier.height(16.dp))
            DetailItem(title = "ID No", value = profile.id_no)
            Spacer(modifier = Modifier.height(16.dp))
            DetailItem(title = "Npwp", value = profile.npwp)



            Spacer(modifier = Modifier.height(32.dp))
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black.copy(0.1f))
            )
            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "Device Information",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight(500)
                )
            )
            Spacer(modifier = Modifier.height(28.dp))


            DetailItem(title = "Primary Device", value = primaryDevise)
            Spacer(modifier = Modifier.height(16.dp))
            DetailItem(title = "App Version", value = appVersion)

            Spacer(modifier = Modifier.height(87.dp))

        }
        

    }

}

@Composable
fun DetailItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight(400),
                color = Color.Black.copy(0.5f)
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight(500),
            )
        )
    }
}