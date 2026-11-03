package com.openrgb.remote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusBanner(
    hasIrEmitter: Boolean,
    modifier: Modifier = Modifier
) {
    val indicatorColor = if (hasIrEmitter) Color(0xFF00E676) else Color(0xFFFF9100)
    val text = if (hasIrEmitter) "Xiaomi / Dahili IR Verici Hazır" else "IR Donanımı Bulunamadı (Simülasyon)"

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1E1E1E))
            .border(1.dp, Color(0xFF2C2C2C), RoundedCornerShape(16.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(indicatorColor)
        )
        Text(
            text = text,
            color = Color(0xFFB0BEC5),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
