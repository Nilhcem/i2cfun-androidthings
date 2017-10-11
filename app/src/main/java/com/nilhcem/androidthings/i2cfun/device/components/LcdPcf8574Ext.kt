package com.nilhcem.androidthings.i2cfun.device.components

import com.nilhcem.androidthings.driver.lcdpcf8574.LcdPcf8574
import com.nilhcem.androidthings.i2cfun.device.components.ArduinoFanI2C.Speed.*

fun LcdPcf8574.showSpeed(speed: ArduinoFanI2C.Speed) {
    clear()
    print("Speed is ${speed.name}")
    setCursor(0, 1)

    print(when (speed) {
        LOW -> "Kinda useless"
        MEDIUM -> "Nice!"
        HIGH -> "Not too cold?"
    })
}
