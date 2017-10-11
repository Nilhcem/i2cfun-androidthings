package com.nilhcem.androidthings.i2cfun.device.components

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.google.android.things.contrib.driver.ssd1306.BitmapHelper
import com.google.android.things.contrib.driver.ssd1306.Ssd1306
import com.nilhcem.androidthings.i2cfun.R
import com.nilhcem.androidthings.i2cfun.device.components.ArduinoFanI2C.Speed.*

fun Ssd1306.showSpeed(speed: ArduinoFanI2C.Speed, resources: Resources) {
    val resId = when (speed) {
        LOW -> R.drawable.ssd1306_low
        MEDIUM -> R.drawable.ssd1306_medium
        HIGH -> R.drawable.ssd1306_high
    }

    clearPixels()
    val bmp = BitmapFactory.decodeResource(resources, resId)
    BitmapHelper.setBmpData(this, 0, 0, bmp, false)
    show()
}
