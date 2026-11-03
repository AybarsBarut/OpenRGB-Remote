# 📱 OpenRGB Remote (Açık Kaynak LED Kumandası)

Play Store'daki aşırı reklamlı RGB LED kumanda uygulamalarına (**`com.ledremote.led_remote`**) alternatif olarak geliştirilmiş, **%100 Açık Kaynak**, **Sıfır Reklam**, **Sıfır Veri Toplama** ve **İnternet İzni Gerektirmeyen** yerel Android uygulaması.

Xiaomi, Redmi, Poco ve dahili Kızılötesi (IR Blaster) vericisine sahip tüm Android cihazlarla doğrudan uyumludur.

---

## ✨ Özellikler

- **🚫 Sıfır Reklam & Sıfır Takip:** İnternet erişim izni bile bulunmaz (`android.permission.INTERNET` yoktur). Arka planda çalışmaz, pil tüketmez.
- **⚡ Dahili IR Blaster Desteği:** Android `ConsumerIrManager` API'si üzerinden tam uyumlu 38 kHz NEC modülasyonu ile sinyal gönderir.
- **🎮 Çoklu Kumanda Desteği:**
  - **24 Tuşlu Standart Kumanda:** Açma/Kapama, Parlaklık +/- , 16 Statik Renk, Flaş, Strobe, Fade, Smooth.
  - **44 Tuşlu Gelişmiş Kumanda:** 20 Statik Renk, Bağımsız R/G/B artırma-azaltma tuşları, Quick/Slow hız ayarları, DIY 1-6 özel hafıza tuşları, Auto, Flash, Jump 3/7, Fade 3/7.
- **📳 Haptik (Dokunsal) Geri Bildirim:** Fiziksel kumanda hissi veren hafif titreşim motoru (istendiğinde tek dokunuşla kapatılabilir).
- **💡 Sanal IR LED Göstergesi:** Kumandadan sinyal iletildiği anda üst kısımdaki kızılötesi kubbe animasyonlu olarak parlar ve gönderilen 32-bit HEX kodunu anlık ekrana yansıtır.
- **🎨 Modern & Karanlık Tasarım:** Gece karanlık odada göz yormayan, Nothing OS ve OLED dostu modern arayüz.

---

## 📲 Xiaomi Telefona Yükleme ve Test

Hazır derlenmiş APK dosyası proje ana dizinindedir:
`c:\Users\4rchura\Documents\rgb_controller\OpenRGB-Remote.apk`

### Yöntem 1: Telefonu Bilgisayara Bağlayarak (ADB ile Tek Komut)
1. Xiaomi telefonunuzda **Ayarlar > Telefon Hakkında** bölümüne gidin ve **MIUI/HyperOS sürümü** yazısına 7 kez art arda basarak Geliştirici Seçeneklerini açın.
2. **Ek Ayarlar > Geliştirici Seçenekleri**ne gidin:
   - **USB Hata Ayıklama**'yı açın.
   - *(Xiaomi'ye özel)* **USB üzerinden yükle** ve **USB hata ayıklama (Güvenlik ayarları)** seçeneklerini de aktif edin.
3. Telefonu USB kablosuyla bilgisayara bağlayın.
4. Terminalden şu komutu çalıştırarak yükleyin:
   ```powershell
   adb install OpenRGB-Remote.apk
   ```

### Yöntem 2: APK Dosyasını Doğrudan Telefona Atarak
1. Proje klasöründeki [OpenRGB-Remote.apk](OpenRGB-Remote.apk) dosyasını Bluetooth, WhatsApp, Telegram veya USB dosya aktarımı ile Xiaomi telefonunuza gönderin.
2. Telefonda dosya yöneticisinden APK'ya tıklayıp yüklemeyi onaylayın.
3. Uygulamayı açtığınızda üstte yeşil renkte **"Xiaomi / Dahili IR Verici Hazır"** ibaresini göreceksiniz. LED şeridinize doğrultup test edebilirsiniz!

---

## 🛠️ Teknik Detaylar & NEC Protokolü

Standart RGB LED kumandaları **NEC 32-bit** kızılötesi protokolünü kullanır:
- **Taşıyıcı Frekansı:** 38,000 Hz (38 kHz)
- **Başlık (Leader):** 9000 µs Darbe (Mark) + 4500 µs Boşluk (Space)
- **Mantıksal '1':** 560 µs Darbe + 1690 µs Boşluk
- **Mantıksal '0':** 560 µs Darbe + 560 µs Boşluk
- **Durdurma Biti:** 560 µs Darbe

### Örnek 24 Tuşlu HEX Kodları (`0x00F7....`)
| Tuş | HEX Kodu | İşlev |
| :--- | :--- | :--- |
| **B+** | `0x00F700FF` | Parlaklık / Hız Artır |
| **B-** | `0x00F7807F` | Parlaklık / Hız Azalt |
| **OFF** | `0x00F740BF` | Kapat |
| **ON** | `0x00F7C03F` | Aç |
| **R** | `0x00F720DF` | Kırmızı |
| **G** | `0x00F7A05F` | Yeşil |
| **B** | `0x00F7609F` | Mavi |
| **W** | `0x00F7E01F` | Beyaz |
| **FLASH** | `0x00F7D02F` | Flaş Modu |
| **STROBE** | `0x00F7F00F` | Strobe Modu |
| **FADE** | `0x00F7C837` | Yumuşak Renk Geçişi |
| **SMOOTH** | `0x00F7E817` | Renk Atlama |

---

## 📂 Proje Mimarisi

- `app/src/main/kotlin/com/openrgb/remote/ir/`:
  - `NecProtocol.kt`: Standart NEC 38kHz darbe ve zamanlama oluşturucusu.
  - `IrTransmitter.kt`: Android `ConsumerIrManager` ve titreşim entegrasyonu.
- `app/src/main/kotlin/com/openrgb/remote/model/`:
  - `RemoteModels.kt`: 24 ve 44 tuşlu kumandaların tuş matrisi, renkleri ve HEX kodları.
- `app/src/main/kotlin/com/openrgb/remote/ui/`:
  - `screens/RemoteScreen.kt`: Ana kumanda gövdesi, mod geçişleri ve komut izleyici.
  - `components/RemoteButton.kt`: Dokunsal, fiziksel tuş hissi veren dinamik buton bileşeni.
  - `components/IrEmitterDome.kt`: Sinyal gönderiminde parlayan sanal kızılötesi LED kubbesi.
