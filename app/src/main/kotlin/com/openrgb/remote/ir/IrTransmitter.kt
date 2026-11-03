package com.openrgb.remote.ir

import android.content.Context
import android.hardware.ConsumerIrManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log

class IrTransmitter(private val context: Context) {

    private val irManager: ConsumerIrManager? = try {
        context.getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager
    } catch (e: Exception) {
        Log.e(TAG, "Failed to get ConsumerIrManager", e)
        null
    }

    private val vibrator: Vibrator? = try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
            vibratorManager?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        }
    } catch (e: Exception) {
        null
    }

    var hapticFeedbackEnabled: Boolean = true

    /**
     * Returns true if device has a hardware IR emitter (like Xiaomi phones).
     */
    fun hasIrEmitter(): Boolean {
        return irManager?.hasIrEmitter() == true
    }

    /**
     * Transmit a 32-bit NEC IR code.
     * @param hexCode The 32-bit hex code (e.g., 0x00F700FFL for 24-key remote, 0x00FF3AC5L for 44-key)
     * @param carrierFrequency Carrier frequency, defaults to 38000 Hz for NEC
     */
    fun transmitNec(hexCode: Long, carrierFrequency: Int = NecProtocol.CARRIER_FREQUENCY): TransmitResult {
        triggerHaptic()

        if (irManager == null || !irManager.hasIrEmitter()) {
            Log.w(TAG, "Device has no IR emitter. Simulating code: 0x${hexCode.toString(16).uppercase()}")
            return TransmitResult.NoHardware(hexCode)
        }

        return try {
            val pattern = NecProtocol.buildPattern(hexCode)
            irManager.transmit(carrierFrequency, pattern)
            Log.d(TAG, "Transmitted NEC code 0x${hexCode.toString(16).uppercase()} ($carrierFrequency Hz)")
            TransmitResult.Success(hexCode)
        } catch (e: Exception) {
            Log.e(TAG, "Error transmitting IR signal", e)
            TransmitResult.Error("Sinyal iletim hatası: ${e.localizedMessage}")
        }
    }

    private fun triggerHaptic() {
        if (!hapticFeedbackEnabled || vibrator == null) return
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE)
                )
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(25)
            }
        } catch (e: Exception) {
            Log.w(TAG, "Vibration failed", e)
        }
    }

    companion object {
        private const val TAG = "IrTransmitter"
    }
}

sealed class TransmitResult {
    data class Success(val hexCode: Long) : TransmitResult()
    data class NoHardware(val hexCode: Long) : TransmitResult()
    data class Error(val message: String) : TransmitResult()
}
