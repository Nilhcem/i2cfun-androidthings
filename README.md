# Android Things I²C fun

I²C communication sample between an Android Things (Raspberry Pi 3) master and multiple slaves.

Blog post: [http://nilhcem.com/android-things/chaining-i2c-devices][blog-post]


## Arduino sketch

Arduino sketch is located in the `arduino` folder


## Components:

- DC motor
- PN2222 transistor
- 1N4007 diode
- 220Ω resistor
- LCD 1602 with PCF8574 I2C adapter
- SSD1306


## Schematic

![schematic]


## License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[schematic]: https://raw.githubusercontent.com/Nilhcem/i2cfun-androidthings/master/assets/schematic_chain.png
[blog-post]: http://nilhcem.com/android-things/arduino-as-an-i2c-slave
