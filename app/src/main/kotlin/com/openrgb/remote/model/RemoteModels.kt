package com.openrgb.remote.model

import androidx.compose.ui.graphics.Color

enum class KeyCategory {
    POWER,
    BRIGHTNESS,
    COLOR,
    SPEED,
    DIY,
    EFFECT
}

data class RemoteKey(
    val id: String,
    val label: String,
    val hexCode: Long,
    val backgroundColor: Color,
    val textColor: Color = Color.White,
    val subLabel: String? = null,
    val category: KeyCategory = KeyCategory.COLOR,
    val isCircular: Boolean = true
)

enum class RemoteType(val title: String, val subtitle: String, val keyCount: Int) {
    KEY_24("24 Tuşlu Kumanda", "Standart RGB LED Şerit", 24),
    KEY_44("44 Tuşlu Kumanda", "Gelişmiş RGB + DIY Kumanda", 44)
}

object RemotePresets {

    // 24-Key Controller Grid (6 rows x 4 columns)
    val keys24: List<List<RemoteKey>> = listOf(
        // Row 0: Controls
        listOf(
            RemoteKey("b_up", "B+", 0x00F700FFL, Color(0xFF333333), Color(0xFFEEEEEE), "Parlaklık+", KeyCategory.BRIGHTNESS),
            RemoteKey("b_down", "B-", 0x00F7807FL, Color(0xFF333333), Color(0xFFEEEEEE), "Parlaklık-", KeyCategory.BRIGHTNESS),
            RemoteKey("off", "OFF", 0x00F740BFL, Color(0xFF1E1E1E), Color(0xFFFF5252), "Kapat", KeyCategory.POWER),
            RemoteKey("on", "ON", 0x00F7C03FL, Color(0xFF1E1E1E), Color(0xFF69F0AE), "Aç", KeyCategory.POWER)
        ),
        // Row 1: Primary Colors & White
        listOf(
            RemoteKey("r", "R", 0x00F720DFL, Color(0xFFE53935), Color.White, "Kırmızı"),
            RemoteKey("g", "G", 0x00F7A05FL, Color(0xFF43A047), Color.White, "Yeşil"),
            RemoteKey("b", "B", 0x00F7609FL, Color(0xFF1E88E5), Color.White, "Mavi"),
            RemoteKey("w", "W", 0x00F7E01FL, Color(0xFFEEEEEE), Color(0xFF212121), "Beyaz")
        ),
        // Row 2
        listOf(
            RemoteKey("orange_1", "", 0x00F710EFL, Color(0xFFFB8C00), Color.White, "Turuncu"),
            RemoteKey("green_1", "", 0x00F7906FL, Color(0xFF7CB342), Color.White, "Açık Yeşil"),
            RemoteKey("blue_1", "", 0x00F750AFL, Color(0xFF039BE5), Color.White, "Açık Mavi"),
            RemoteKey("flash", "FLASH", 0x00F7D02FL, Color(0xFF424242), Color(0xFFE0E0E0), "Flaş", KeyCategory.EFFECT, isCircular = false)
        ),
        // Row 3
        listOf(
            RemoteKey("orange_2", "", 0x00F730CFL, Color(0xFFFFA000), Color.White, "Koyu Sarı"),
            RemoteKey("cyan_1", "", 0x00F7B04FL, Color(0xFF26A69A), Color.White, "Turkuaz"),
            RemoteKey("purple_1", "", 0x00F7708FL, Color(0xFF5E35B1), Color.White, "Mor"),
            RemoteKey("strobe", "STROBE", 0x00F7F00FL, Color(0xFF424242), Color(0xFFE0E0E0), "Strobe", KeyCategory.EFFECT, isCircular = false)
        ),
        // Row 4
        listOf(
            RemoteKey("yellow_1", "", 0x00F708F7L, Color(0xFFFDD835), Color(0xFF212121), "Sarı"),
            RemoteKey("cyan_2", "", 0x00F78877L, Color(0xFF29B6F6), Color.White, "Gök Mavi"),
            RemoteKey("purple_2", "", 0x00F748B7L, Color(0xFF8E24AA), Color.White, "Açık Mor"),
            RemoteKey("fade", "FADE", 0x00F7C837L, Color(0xFF424242), Color(0xFFE0E0E0), "Geçiş", KeyCategory.EFFECT, isCircular = false)
        ),
        // Row 5
        listOf(
            RemoteKey("yellow_2", "", 0x00F728D7L, Color(0xFFFFEE58), Color(0xFF212121), "Açık Sarı"),
            RemoteKey("aqua_1", "", 0x00F7A857L, Color(0xFF4DD0E1), Color(0xFF212121), "Açık Turkuaz"),
            RemoteKey("pink_1", "", 0x00F76897L, Color(0xFFEC407A), Color.White, "Pembe"),
            RemoteKey("smooth", "SMOOTH", 0x00F7E817L, Color(0xFF424242), Color(0xFFE0E0E0), "Yumuşak", KeyCategory.EFFECT, isCircular = false)
        )
    )

    // 44-Key Controller Grid (11 rows x 4 columns)
    val keys44: List<List<RemoteKey>> = listOf(
        // Row 0: Top Controls
        listOf(
            RemoteKey("b_up44", "▲", 0x00FF3AC5L, Color(0xFF333333), Color(0xFFEEEEEE), "Parlaklık+", KeyCategory.BRIGHTNESS),
            RemoteKey("b_down44", "▼", 0x00FFBA45L, Color(0xFF333333), Color(0xFFEEEEEE), "Parlaklık-", KeyCategory.BRIGHTNESS),
            RemoteKey("play44", "▶||", 0x00FF827DL, Color(0xFF333333), Color(0xFFEEEEEE), "Oynat/Dur", KeyCategory.POWER),
            RemoteKey("pwr44", "⏻", 0x00FF02FDL, Color(0xFFB71C1C), Color.White, "Güç", KeyCategory.POWER)
        ),
        // Row 1: Colors R1, G1, B1, W1
        listOf(
            RemoteKey("r1", "R", 0x00FF1AE5L, Color(0xFFE53935), Color.White, "Kırmızı"),
            RemoteKey("g1", "G", 0x00FF9A65L, Color(0xFF43A047), Color.White, "Yeşil"),
            RemoteKey("b1", "B", 0x00FFA25DL, Color(0xFF1E88E5), Color.White, "Mavi"),
            RemoteKey("w1", "W", 0x00FF22DDL, Color(0xFFEEEEEE), Color(0xFF212121), "Beyaz")
        ),
        // Row 2: Colors R2, G2, B2, W2
        listOf(
            RemoteKey("r2", "", 0x00FF2AD5L, Color(0xFFF4511E), Color.White, "Kızıl Turuncu"),
            RemoteKey("g2", "", 0x00FFAA55L, Color(0xFF66BB6A), Color.White, "Açık Yeşil"),
            RemoteKey("b2", "", 0x00FF926DL, Color(0xFF1976D2), Color.White, "Kraliyet Mavisi"),
            RemoteKey("w2", "", 0x00FF12EDL, Color(0xFFFFECB3), Color(0xFF212121), "Sıcak Beyaz")
        ),
        // Row 3: Colors R3, G3, B3, W3
        listOf(
            RemoteKey("r3", "", 0x00FF0AF5L, Color(0xFFFB8C00), Color.White, "Turuncu"),
            RemoteKey("g3", "", 0x00FF8A75L, Color(0xFF26A69A), Color.White, "Zümrüt Yeşili"),
            RemoteKey("b3", "", 0x00FFB24DL, Color(0xFF5E35B1), Color.White, "Koyu Mor"),
            RemoteKey("w3", "", 0x00FF32CDL, Color(0xFFE0F7FA), Color(0xFF212121), "Soğuk Beyaz")
        ),
        // Row 4: Colors R4, G4, B4, W4
        listOf(
            RemoteKey("r4", "", 0x00FF38C7L, Color(0xFFFFA000), Color.White, "Koyu Sarı"),
            RemoteKey("g4", "", 0x00FFB847L, Color(0xFF00ACC1), Color.White, "Camgöbeği"),
            RemoteKey("b4", "", 0x00FF7887L, Color(0xFF8E24AA), Color.White, "Eflatun"),
            RemoteKey("w4", "", 0x00FFF807L, Color(0xFFE1F5FE), Color(0xFF212121), "Açık Mavi Beyaz")
        ),
        // Row 5: Colors R5, G5, B5, W5
        listOf(
            RemoteKey("r5", "", 0x00FF18E7L, Color(0xFFFDD835), Color(0xFF212121), "Sarı"),
            RemoteKey("g5", "", 0x00FF9867L, Color(0xFF4DD0E1), Color(0xFF212121), "Açık Turkuaz"),
            RemoteKey("b5", "", 0x00FF58A7L, Color(0xFFEC407A), Color.White, "Pembe"),
            RemoteKey("w5", "", 0x00FFD827L, Color(0xFFFFF9C4), Color(0xFF212121), "Doğal Beyaz")
        ),
        // Row 6: Color Up adjustments & Quick
        listOf(
            RemoteKey("rup", "▲", 0x00FF28D7L, Color(0xFF2A2A2A), Color(0xFFFF5252), "R+", KeyCategory.DIY, isCircular = false),
            RemoteKey("gup", "▲", 0x00FFA857L, Color(0xFF2A2A2A), Color(0xFF69F0AE), "G+", KeyCategory.DIY, isCircular = false),
            RemoteKey("bup", "▲", 0x00FF6897L, Color(0xFF2A2A2A), Color(0xFF448AFF), "B+", KeyCategory.DIY, isCircular = false),
            RemoteKey("quick", "QUICK", 0x00FFE817L, Color(0xFF333333), Color(0xFFE0E0E0), "Hızlı", KeyCategory.SPEED, isCircular = false)
        ),
        // Row 7: Color Down adjustments & Slow
        listOf(
            RemoteKey("rdown", "▼", 0x00FF08F7L, Color(0xFF2A2A2A), Color(0xFFFF5252), "R-", KeyCategory.DIY, isCircular = false),
            RemoteKey("gdown", "▼", 0x00FF8877L, Color(0xFF2A2A2A), Color(0xFF69F0AE), "G-", KeyCategory.DIY, isCircular = false),
            RemoteKey("bdown", "▼", 0x00FF48B7L, Color(0xFF2A2A2A), Color(0xFF448AFF), "B-", KeyCategory.DIY, isCircular = false),
            RemoteKey("slow", "SLOW", 0x00FFC837L, Color(0xFF333333), Color(0xFFE0E0E0), "Yavaş", KeyCategory.SPEED, isCircular = false)
        ),
        // Row 8: DIY 1-3 & Auto
        listOf(
            RemoteKey("diy1", "DIY1", 0x00FF30CFL, Color(0xFF263238), Color(0xFFECEFF1), "Özel 1", KeyCategory.DIY, isCircular = false),
            RemoteKey("diy2", "DIY2", 0x00FFB04FL, Color(0xFF263238), Color(0xFFECEFF1), "Özel 2", KeyCategory.DIY, isCircular = false),
            RemoteKey("diy3", "DIY3", 0x00FF708FL, Color(0xFF263238), Color(0xFFECEFF1), "Özel 3", KeyCategory.DIY, isCircular = false),
            RemoteKey("auto", "AUTO", 0x00FFF00FL, Color(0xFF37474F), Color(0xFFECEFF1), "Otomatik", KeyCategory.EFFECT, isCircular = false)
        ),
        // Row 9: DIY 4-6 & Flash
        listOf(
            RemoteKey("diy4", "DIY4", 0x00FF10EFL, Color(0xFF263238), Color(0xFFECEFF1), "Özel 4", KeyCategory.DIY, isCircular = false),
            RemoteKey("diy5", "DIY5", 0x00FF906FL, Color(0xFF263238), Color(0xFFECEFF1), "Özel 5", KeyCategory.DIY, isCircular = false),
            RemoteKey("diy6", "DIY6", 0x00FF50AFL, Color(0xFF263238), Color(0xFFECEFF1), "Özel 6", KeyCategory.DIY, isCircular = false),
            RemoteKey("flash44", "FLASH", 0x00FFD02FL, Color(0xFF37474F), Color(0xFFECEFF1), "Flaş", KeyCategory.EFFECT, isCircular = false)
        ),
        // Row 10: Jump & Fade Effects
        listOf(
            RemoteKey("jump3", "JUMP3", 0x00FF20DFL, Color(0xFF212121), Color(0xFFCFD8DC), "Atlama 3", KeyCategory.EFFECT, isCircular = false),
            RemoteKey("jump7", "JUMP7", 0x00FFA05FL, Color(0xFF212121), Color(0xFFCFD8DC), "Atlama 7", KeyCategory.EFFECT, isCircular = false),
            RemoteKey("fade3", "FADE3", 0x00FF609FL, Color(0xFF212121), Color(0xFFCFD8DC), "Geçiş 3", KeyCategory.EFFECT, isCircular = false),
            RemoteKey("fade7", "FADE7", 0x00FFE01FL, Color(0xFF212121), Color(0xFFCFD8DC), "Geçiş 7", KeyCategory.EFFECT, isCircular = false)
        )
    )
}
