#include <Wire.h>

const uint8_t MOTOR_PIN = 3;
const uint8_t I2C_ADDRESS = 0x42;
const uint8_t COMMAND_ON_OFF = 0x01;
const uint8_t COMMAND_SPEED = 0x02;
const uint8_t DEFAULT_SPEED = 150;

uint8_t opcode;
uint8_t speed;

void setup() {
  pinMode(MOTOR_PIN, OUTPUT);

  Wire.begin(I2C_ADDRESS);
  Wire.onRequest(requestEvent);
  Wire.onReceive(receiveEvent);
}

void loop() {
  analogWrite(MOTOR_PIN, speed);
  delay(1000);
}

void receiveEvent(int bytes) {
  opcode = Wire.read();

  if (bytes > 1) {
    speed = Wire.read();

    if (opcode == COMMAND_ON_OFF) {
      speed = (speed == 0x01) ? DEFAULT_SPEED : 0;
    }
  }
}

void requestEvent() {
  switch (opcode) {
    case COMMAND_SPEED:
      Wire.write((uint8_t *)&speed, sizeof(speed));
      break;
    default:
      Wire.write(0);
      break;
  }
}
