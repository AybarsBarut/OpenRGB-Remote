package com.openrgb.remote

import com.openrgb.remote.ir.NecProtocol
import org.junit.Assert.assertEquals
import org.junit.Test

class IrTransmitterTest {

    @Test
    fun testNecPatternGenerationLength() {
        val pattern = NecProtocol.buildPattern(0x00F700FFL) // 24-key Brightness+
        // 2 header + 32*2 bits + 1 stop bit = 67 elements
        assertEquals(67, pattern.size)
        // Leader check
        assertEquals(NecProtocol.HDR_MARK, pattern[0])
        assertEquals(NecProtocol.HDR_SPACE, pattern[1])
        // Stop bit check
        assertEquals(NecProtocol.STOP_MARK, pattern[66])
    }

    @Test
    fun testNecBitPatterns() {
        // Test single bit logic: MSB of 0x80000000 is 1, remaining 31 bits are 0
        val pattern = NecProtocol.buildPattern(0x80000000L)
        // First bit (index 2: mark, index 3: space)
        assertEquals(NecProtocol.BIT_MARK, pattern[2])
        assertEquals(NecProtocol.ONE_SPACE, pattern[3])

        // Second bit (index 4: mark, index 5: space) -> bit is 0
        assertEquals(NecProtocol.BIT_MARK, pattern[4])
        assertEquals(NecProtocol.ZERO_SPACE, pattern[5])
    }
}
