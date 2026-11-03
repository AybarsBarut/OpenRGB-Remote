# 💡 OpenRGB Remote (Ad-Free Open-Source LED IR Remote)

[![Android Platform](https://img.shields.io/badge/Platform-Android%207.0%2B-3DDC84?style=flat-square&logo=android&logoColor=white)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.3-7F52FF?style=flat-square&logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4?style=flat-square&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Ads](https://img.shields.io/badge/Ads-0%20(Completely%20Ad--Free)-success?style=flat-square)]()
[![Telemetry](https://img.shields.io/badge/Telemetry-None%20(100%25%20Offline)-brightgreen?style=flat-square)]()
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat-square)](LICENSE)

An open-source, ad-free, privacy-respecting Android infrared (IR) remote control app for standard **24-key** and **44-key RGB LED strip lights**.

Built as a direct replacement for ad-bloated Play Store apps like `com.ledremote.led_remote`. Works natively on **Xiaomi, Redmi, Poco**, and any other Android smartphone equipped with a built-in hardware IR blaster.

---

## ⚡ Comparison

| Feature | Play Store Apps (`com.ledremote.led_remote`) | OpenRGB Remote |
| :--- | :---: | :---: |
| **Ads** | ❌ Fullscreen ads on almost every click | ✅ **Zero Ads (Forever)** |
| **Internet Access** | ⚠️ Required (`android.permission.INTERNET`) | ✅ **Not requested (100% Offline)** |
| **Background Services & Trackers** | ⚠️ Analytics, Ad SDKs, tracking | ✅ **None. Zero telemetry** |
| **Source Code** | ❌ Proprietary / Closed | ✅ **100% Open Source** |
| **Supported Remotes** | 24-key & 44-key | **24-key & 44-key (Instant Switch)** |
| **Hardware IR Blaster (Xiaomi)** | Yes | **Yes (Android `ConsumerIrManager`)** |
| **Haptic Feedback** | Basic | **Yes (Custom tactile click, toggleable)** |
| **Visual IR LED Indicator** | No | **Yes (Pulsing neon IR dome)** |
| **Live HEX Code Monitor** | No | **Yes (Displays transmitted 32-bit HEX)** |

---

## 📱 Features

- **🎯 Standard 24-Key Remote:**
  - Brightness Up / Down, Power OFF / ON
  - 16 Solid colors (Red, Green, Blue, White + 12 shades)
  - 4 Dynamic modes: Flash, Strobe, Fade, Smooth
- **🎛️ Advanced 44-Key Remote:**
  - 20 Preset color buttons
  - Fine-tuning RGB Up / Down arrow buttons (R+, G+, B+, R-, G-, B-)
  - Quick & Slow animation speed keys
  - DIY 1–6 user preset slots
  - Auto, Flash, Jump 3, Jump 7, Fade 3, Fade 7 modes
- **⚡ Native IR Transmission:**
  - Directly modulates the hardware IR LED at **38 kHz** using Android's native `ConsumerIrManager` API.
  - No external dongles, bridges, or Wi-Fi hubs required.
- **📳 Tactile & Visual Feedback:**
  - Haptic vibration replicates the feel of a physical silicone remote button.
  - Top-mounted virtual IR dome flashes when a code is broadcast.
  - Status banner confirms when Xiaomi / internal IR hardware is detected.

---

## 📥 Installation

Download the latest APK from the [Releases](https://github.com/AybarsBarut/rgb-led-remote/releases) tab.

### Option A: Direct Installation on Phone
1. Transfer the `.apk` file to your Xiaomi / Android phone (via USB, Bluetooth, or messaging).
2. Tap the file in your device's file manager and allow installation.
3. Open the app, aim the top of your phone at the LED strip receiver, and press any button!

### Option B: Via ADB (Command Line)
```bash
adb install OpenRGB-Remote.apk
```

---

## 📡 IR Protocol & NEC Codes

Standard LED strip controllers use the **NEC 32-bit Transmission Protocol**:
- **Carrier Frequency:** 38,000 Hz (38 kHz)
- **Header:** 9,000 µs pulse (mark) + 4,500 µs pause (space)
- **Bit 1:** 560 µs mark + 1,690 µs space
- **Bit 0:** 560 µs mark + 560 µs space
- **Stop Bit:** 560 µs mark

### 24-Key Remote Mapping Table

| Button | HEX Code | Action | Button | HEX Code | Action |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **B+** | `0x00F700FF` | Brightness Up | **B-** | `0x00F7807F` | Brightness Down |
| **OFF** | `0x00F740BF` | Power Off | **ON** | `0x00F7C03F` | Power On |
| **R** | `0x00F720DF` | Red | **G** | `0x00F7A05F` | Green |
| **B** | `0x00F7609F` | Blue | **W** | `0x00F7E01F` | White |
| **Orange 1** | `0x00F710EF` | Orange | **Pea Green** | `0x00F7906F` | Light Green |
| **Dark Cyan** | `0x00F750AF` | Cyan / Sky Blue | **FLASH** | `0x00F7D02F` | Flash Mode |
| **Dark Yellow** | `0x00F730CF` | Amber | **Turquoise** | `0x00F7B04F` | Mint |
| **Purple** | `0x00F7708F` | Indigo / Purple | **STROBE** | `0x00F7F00F` | Strobe Mode |
| **Yellow** | `0x00F708F7` | Yellow | **Sky Blue** | `0x00F78877` | Light Blue |
| **Pink** | `0x00F748B7` | Fuchsia / Pink | **FADE** | `0x00F7C837` | Fade Mode |
| **Pale Yellow** | `0x00F728D7` | Light Yellow | **Aqua** | `0x00F7A857` | Aquamarine |
| **Magenta** | `0x00F76897` | Rose Pink | **SMOOTH** | `0x00F7E817` | Smooth Mode |

---

## 🛠️ Building from Source

### Prerequisites
- JDK 17 or JDK 21
- Android SDK (API 34+ recommended)

### Build Commands
```bash
# Clone the repository
git clone https://github.com/AybarsBarut/rgb-led-remote.git
cd rgb-led-remote

# Run unit tests
./gradlew testDebugUnitTest

# Build debug APK
./gradlew assembleDebug
```
The compiled APK will be generated at `app/build/outputs/apk/debug/app-debug.apk`.

---

## 🇹🇷 Türkçe Özet

Play Store'daki aşırı reklamlı uygulamalara kızıp geliştirdiğimiz bu açık kaynaklı uygulama; **Xiaomi, Redmi ve Poco** gibi kızılötesi vericili telefonlarınızı anında 24 tuşlu ve 44 tuşlu RGB LED şerit kumandasına dönüştürür:
- **Tamamen Reklamsız:** Sıfır reklam, sıfır takip, internet izni dahi istemez.
- **Dahili IR Desteği:** Android `ConsumerIrManager` servisi ile doğrudan 38kHz NEC sinyali iletir.
- **Hazır APK:** [Releases](https://github.com/AybarsBarut/rgb-led-remote/releases) sekmesinden tek tıkla APK'yı indirip telefonunuza kurabilirsiniz.

---

## 📄 License

This project is licensed under the Apache License 2.0. See [LICENSE](LICENSE) for details.
