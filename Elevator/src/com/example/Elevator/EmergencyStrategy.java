package com.example.Elevator;

public class EmergencyStrategy {
    public void handleEmergency(Elevator elevator) {
        System.out.println("Emergency strategy triggered for elevator " + elevator.getElevatorId());
    }
}
