package com.openrgb.remote.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun IrEmitterDome(
    isTransmitting: Boolean,
    modifier: Modifier = Modifier
) {
    val glowColor by animateColorAsState(
        targetValue = if (isTransmitting) Color(0xFFFF1744) else Color(0xFF263238),
        animationSpec = tween(durationMillis = if (isTransmitting) 50 else 300),
        label = "ir_glow"
    )

    val innerGlow by animateColorAsState(
        targetValue = if (isTransmitting) Color(0xFFFF8A80) else Color(0xFF101010),
        animationSpec = tween(durationMillis = if (isTransmitting) 50 else 300),
        label = "ir_inner_glow"
    )

    Box(
        modifier = modifier
            .size(width = 48.dp, height = 18.dp)
            .shadow(
                elevation = if (isTransmitting) 16.dp else 4.dp,
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
                spotColor = glowColor,
                ambientColor = glowColor
            )
            .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF121212),
                        glowColor.copy(alpha = 0.8f)
                    )
                )
            )
            .border(
                width = 1.dp,
                color = if (isTransmitting) Color(0xFFFF5252) else Color(0xFF37474F),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        // Small inner bulb filament
        Box(
            modifier = Modifier
                .size(width = 16.dp, height = 8.dp)
                .clip(CircleShape)
                .background(innerGlow)
        )
    }
}
