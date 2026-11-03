package com.openrgb.remote.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openrgb.remote.model.RemoteKey

@Composable
fun RemoteButton(
    keyItem: RemoteKey,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.90f else 1.0f,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 600f),
        label = "button_scale"
    )

    val shape = if (keyItem.isCircular) CircleShape else RoundedCornerShape(10.dp)

    Box(
        modifier = modifier
            .scale(scale)
            .aspectRatio(if (keyItem.isCircular) 1f else 1.25f)
            .padding(2.5.dp)
            .shadow(
                elevation = if (isPressed) 1.dp else 4.dp,
                shape = shape,
                ambientColor = Color.Black.copy(alpha = 0.5f),
                spotColor = Color.Black.copy(alpha = 0.8f)
            )
            .clip(shape)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        keyItem.backgroundColor.copy(alpha = 0.95f),
                        keyItem.backgroundColor
                    )
                )
            )
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = if (isPressed) 0.4f else 0.15f),
                shape = shape
            )
            .pointerInput(keyItem.id) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        try {
                            tryAwaitRelease()
                        } finally {
                            isPressed = false
                        }
                    },
                    onTap = {
                        onClick()
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(2.dp)
        ) {
            if (keyItem.label.isNotEmpty()) {
                Text(
                    text = keyItem.label,
                    color = keyItem.textColor,
                    fontSize = if (keyItem.label.length > 4) 9.sp else if (keyItem.label.length > 2) 11.sp else 13.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}
