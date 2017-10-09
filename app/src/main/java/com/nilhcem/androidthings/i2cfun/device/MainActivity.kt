package com.nilhcem.androidthings.i2cfun.device

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.nilhcem.androidthings.i2cfun.device.components.ArduinoFanI2C
import com.nilhcem.androidthings.i2cfun.device.components.ArduinoFanI2C.Speed

class MainActivity : Activity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName!!

        private const val I2C_PIN_NAME = "I2C1"
        private const val I2C_ADDRESS_ARDUINO = 0x42
    }

    private var fan: ArduinoFanI2C? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fan = ArduinoFanI2C(I2C_PIN_NAME, I2C_ADDRESS_ARDUINO)
    }

    override fun onResume() {
        super.onResume()

        fan?.let { fan ->
            fan.start()
            fan.speed = Speed.HIGH
            Log.i(TAG, "Speed: ${fan.speed}")
            Thread.sleep(5000)
            fan.stop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fan?.close().also { fan = null }
    }
}
