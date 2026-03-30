package com.example.Elevator;

public class AlarmButton extends ElevatorButton {
    @Override
    public void press() {
        System.out.println("Alarm button pressed! Alerting authorities and stopping the elevator.");
    }
}
