package com.nilhcem.androidthings.i2cfun.core.ext

import com.google.android.things.pio.PeripheralManagerService
import java.io.IOException

fun PeripheralManagerService.scanI2cAvailableAddresses(i2cName: String): List<Int> {
    return (0..127).filter { address ->
        openI2cDevice(i2cName, address).use { device ->
            try {
                device.write(ByteArray(1), 1)
                true
            } catch (e: IOException) {
                false
            }
        }
    }
}
