package com.example.Elevator;

public class WeightAlertSensor extends SensorDecorator {
    public WeightAlertSensor(Sensor sensor) {
        super(sensor);
    }

    @Override
    public String readSensor() {
        return super.readSensor() + " | WeightAlertSensor: threshold alert check (placeholder)";
    }
}
