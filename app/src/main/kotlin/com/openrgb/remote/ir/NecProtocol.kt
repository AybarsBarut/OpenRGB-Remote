package com.openrgb.remote.ir

object NecProtocol {
    const val CARRIER_FREQUENCY: Int = 38000 // 38kHz standard

    // Durations in microseconds (µs)
    const val HDR_MARK: Int = 9000
    const val HDR_SPACE: Int = 4500
    const val BIT_MARK: Int = 560
    const val ONE_SPACE: Int = 1690
    const val ZERO_SPACE: Int = 560
    const val STOP_MARK: Int = 560

    /**
     * Builds standard 32-bit NEC pulse timing pattern in microseconds:
     * - Leader mark (9000 µs), Leader space (4500 µs)
     * - 32 bits (MSB first):
     *     Bit 1: 560 µs mark, 1690 µs space
     *     Bit 0: 560 µs mark, 560 µs space
     * - Stop mark (560 µs)
     * Total pulse count: 2 + 32*2 + 1 = 67 elements.
     */
    fun buildPattern(data: Long, bitCount: Int = 32): IntArray {
        val pattern = IntArray(2 + bitCount * 2 + 1)
        var index = 0

        pattern[index++] = HDR_MARK
        pattern[index++] = HDR_SPACE

        for (i in (bitCount - 1) downTo 0) {
            val bit = ((data ushr i) and 1L) == 1L
            pattern[index++] = BIT_MARK
            pattern[index++] = if (bit) ONE_SPACE else ZERO_SPACE
        }

        pattern[index] = STOP_MARK
        return pattern
    }
}
