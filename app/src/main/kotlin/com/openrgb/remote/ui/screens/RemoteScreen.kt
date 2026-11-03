package com.openrgb.remote.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openrgb.remote.ir.IrTransmitter
import com.openrgb.remote.ir.TransmitResult
import com.openrgb.remote.model.RemotePresets
import com.openrgb.remote.model.RemoteType
import com.openrgb.remote.ui.components.AboutDialog
import com.openrgb.remote.ui.components.IrEmitterDome
import com.openrgb.remote.ui.components.RemoteButton
import com.openrgb.remote.ui.components.StatusBanner
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RemoteScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val transmitter = remember { IrTransmitter(context) }
    val scope = rememberCoroutineScope()

    var selectedRemote by remember { mutableStateOf(RemoteType.KEY_24) }
    var isTransmitting by remember { mutableStateOf(false) }
    var lastCommandInfo by remember { mutableStateOf<String?>(null) }
    var hapticEnabled by remember { mutableStateOf(transmitter.hapticFeedbackEnabled) }
    var showAboutDialog by remember { mutableStateOf(false) }

    val hasIr = remember { transmitter.hasIrEmitter() }

    fun sendCommand(hexCode: Long, label: String, subLabel: String?) {
        scope.launch {
            isTransmitting = true
            val name = if (!subLabel.isNullOrBlank()) subLabel else label
            val hexString = "0x" + hexCode.toString(16).uppercase().padStart(8, '0')
            lastCommandInfo = "$name ($hexString)"

            transmitter.transmitNec(hexCode)
            delay(150)
            isTransmitting = false
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFF0C0C0E)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top App Bar / Actions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatusBanner(hasIrEmitter = hasIr)

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Haptic Toggle
                    IconButton(
                        onClick = {
                            hapticEnabled = !hapticEnabled
                            transmitter.hapticFeedbackEnabled = hapticEnabled
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Vibration,
                            contentDescription = "Titreşim",
                            tint = if (hapticEnabled) Color(0xFF00E676) else Color(0xFF616161),
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    // Info Dialog
                    IconButton(
                        onClick = { showAboutDialog = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Hakkında",
                            tint = Color(0xFF90A4AE),
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }

            // Remote Type Switcher (Segmented Control)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF161619))
                    .border(1.dp, Color(0xFF28282D), RoundedCornerShape(12.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                RemoteType.values().forEach { remoteType ->
                    val isSelected = selectedRemote == remoteType
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) Color(0xFF26262B) else Color.Transparent)
                            .clickable { selectedRemote = remoteType }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = remoteType.title,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp,
                            color = if (isSelected) Color(0xFF00E676) else Color(0xFF78909C)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Last Sent Command Monitor
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF141416))
                    .border(1.dp, Color(0xFF222226), RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = lastCommandInfo?.let { "İletildi: $it" } ?: "Komut bekleniyor...",
                    color = if (lastCommandInfo != null) Color(0xFF00E676) else Color(0xFF546E7A),
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Physical Remote Body
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(26.dp),
                        ambientColor = Color.Black,
                        spotColor = Color.Black
                    )
                    .clip(RoundedCornerShape(26.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF202024),
                                Color(0xFF141416),
                                Color(0xFF111113)
                            )
                        )
                    )
                    .border(
                        width = 1.5.dp,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF383840),
                                Color(0xFF1E1E22)
                            )
                        ),
                        shape = RoundedCornerShape(26.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // IR Blaster Dome at the top of the physical casing
                    IrEmitterDome(
                        isTransmitting = isTransmitting,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )

                    // Remote Keypad Grid
                    val currentKeyGrid = if (selectedRemote == RemoteType.KEY_24) {
                        RemotePresets.keys24
                    } else {
                        RemotePresets.keys44
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        contentPadding = PaddingValues(bottom = 12.dp)
                    ) {
                        items(currentKeyGrid) { rowKeys ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                for (keyItem in rowKeys) {
                                    RemoteButton(
                                        keyItem = keyItem,
                                        onClick = {
                                            sendCommand(keyItem.hexCode, keyItem.label, keyItem.subLabel)
                                        },
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showAboutDialog) {
        AboutDialog(onDismiss = { showAboutDialog = false })
    }
}
