package com.openrgb.remote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF1E1E1E),
        titleContentColor = Color.White,
        textContentColor = Color(0xFFB0BEC5),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Bolt,
                    contentDescription = null,
                    tint = Color(0xFF00E676),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "OpenRGB Remote",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Play Store'daki aşırı reklamlı kumanda uygulamalarına alternatif olarak tamamen açık kaynak ve reklamsız olarak geliştirilmiştir.",
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                FeatureItem(
                    icon = Icons.Default.Shield,
                    tint = Color(0xFF40C4FF),
                    title = "%100 Reklamsız & Güvenli",
                    desc = "Sıfır reklam, sıfır veri toplama, internet izni bile istemez."
                )

                FeatureItem(
                    icon = Icons.Default.Bolt,
                    tint = Color(0xFFFFD600),
                    title = "Xiaomi IR Blaster Desteği",
                    desc = "Android ConsumerIR API'si ile doğrudan 38kHz NEC sinyali iletir."
                )

                FeatureItem(
                    icon = Icons.Default.CheckCircle,
                    tint = Color(0xFF69F0AE),
                    title = "24 & 44 Tuşlu Kumandalar",
                    desc = "Piyasadaki tüm standart RGB LED şerit alıcılarıyla uyumludur."
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2C2C2C),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Tamam")
            }
        }
    )
}

@Composable
private fun FeatureItem(
    icon: ImageVector,
    tint: Color,
    title: String,
    desc: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .size(18.dp)
                .padding(top = 2.dp)
        )
        Column {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Color.White
            )
            Text(
                text = desc,
                fontSize = 11.sp,
                color = Color(0xFF90A4AE),
                lineHeight = 15.sp
            )
        }
    }
}
