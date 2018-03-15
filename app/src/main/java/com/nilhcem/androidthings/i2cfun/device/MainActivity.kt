package com.nilhcem.androidthings.i2cfun.device

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.android.things.contrib.driver.ssd1306.Ssd1306
import com.google.android.things.pio.PeripheralManager
import com.nilhcem.androidthings.driver.lcdpcf8574.LcdPcf8574
import com.nilhcem.androidthings.i2cfun.core.ext.scanI2cAvailableAddresses
import com.nilhcem.androidthings.i2cfun.device.components.ArduinoFanI2C
import com.nilhcem.androidthings.i2cfun.device.components.ArduinoFanI2C.Speed
import com.nilhcem.androidthings.i2cfun.device.components.showSpeed
import java.util.*

class MainActivity : Activity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName!!

        private const val I2C_PIN_NAME = "I2C1"
        private const val I2C_ADDRESS_LCD = 0x3F
        private const val I2C_ADDRESS_OLED = 0x3C
        private const val I2C_ADDRESS_ARDUINO = 0x42
    }

    private var lcd: LcdPcf8574? = null
    private var oled: Ssd1306? = null
    private var fan: ArduinoFanI2C? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scanI2cDevices()
        lcd = LcdPcf8574(I2C_PIN_NAME, I2C_ADDRESS_LCD).apply {
            begin(16, 2)
            setBacklight(true)
        }
        oled = Ssd1306(I2C_PIN_NAME, I2C_ADDRESS_OLED).apply {
            clearPixels()
        }
        fan = ArduinoFanI2C(I2C_PIN_NAME, I2C_ADDRESS_ARDUINO)
    }

    override fun onResume() {
        super.onResume()

        fan!!.start()

        Speed.values().forEach { speed ->
            fan!!.speed = speed
            lcd!!.showSpeed(speed)
            oled!!.showSpeed(speed, resources)
            wait1sec()
        }

        fan!!.stop()
        lcd!!.clear()
        oled!!.clearPixels()
    }

    override fun onDestroy() {
        super.onDestroy()
        lcd?.close().also { lcd = null }
        oled?.close().also { oled = null }
        fan?.close().also { fan = null }
    }

    private fun scanI2cDevices() {
        Log.i(TAG, "Scanning I2C devices")
        PeripheralManager.getInstance().scanI2cAvailableAddresses(I2C_PIN_NAME)
                .map { String.format(Locale.US, "0x%02X", it) }
                .forEach { address -> Log.i(TAG, "Found: $address") }
    }

    private fun wait1sec() = Thread.sleep(5000)
}
