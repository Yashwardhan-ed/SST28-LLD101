package com.example.Elevator;

public abstract class SensorDecorator implements Sensor {
    protected final Sensor sensor;

    protected SensorDecorator(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String readSensor() {
        return sensor.readSensor();
    }
}
