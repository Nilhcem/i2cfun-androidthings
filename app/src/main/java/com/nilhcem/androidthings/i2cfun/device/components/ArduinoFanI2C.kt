package com.nilhcem.androidthings.i2cfun.device.components

import com.google.android.things.pio.I2cDevice
import com.google.android.things.pio.PeripheralManagerService
import com.nilhcem.androidthings.i2cfun.core.ext.toPositiveInt

class ArduinoFanI2C(i2cName: String, i2cAddress: Int) : AutoCloseable {

    companion object {
        private const val COMMAND_ON_OFF = 0x01
        private const val COMMAND_SPEED = 0x02

        private const val VALUE_ON = 1.toByte()
        private const val VALUE_OFF = 0.toByte()
    }

    enum class Speed(val i2cValue: Int) {
        LOW(150), MEDIUM(200), HIGH(250);

        companion object {
            fun fromValue(i2cValue: Int) = Speed.values().firstOrNull { it.i2cValue == i2cValue } ?: MEDIUM
        }
    }

    private var device: I2cDevice? = null

    init {
        device = PeripheralManagerService().openI2cDevice(i2cName, i2cAddress)
    }

    var speed: Speed
        get() = Speed.fromValue(device?.readRegByte(COMMAND_SPEED)?.toPositiveInt() ?: 0)
        set(value) {
            device?.writeRegByte(COMMAND_SPEED, value.i2cValue.toByte())
        }

    override fun close() {
        device?.close().also { device = null }
    }

    fun start() = device?.writeRegByte(COMMAND_ON_OFF, VALUE_ON)

    fun stop() = device?.writeRegByte(COMMAND_ON_OFF, VALUE_OFF)
}
