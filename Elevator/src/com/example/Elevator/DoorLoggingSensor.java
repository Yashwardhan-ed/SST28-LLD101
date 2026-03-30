package com.example.Elevator;

public class DoorLoggingSensor extends SensorDecorator {
    public DoorLoggingSensor(Sensor sensor) {
        super(sensor);
    }

    @Override
    public String readSensor() {
        return super.readSensor() + " | DoorLoggingSensor: door event logged (placeholder)";
    }
}
