package com.example.Elevator;

public class CurrentFloorSensor extends SensorDecorator {
    public CurrentFloorSensor(Sensor sensor) {
        super(sensor);
    }

    @Override
    public String readSensor() {
        return super.readSensor() + " | CurrentFloorSensor: floor sync check (placeholder)";
    }
}
