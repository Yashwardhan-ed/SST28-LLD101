package com.example.Elevator;

public class OpenDoorButton extends ElevatorButton {
    @Override
    public void press() {
        System.out.println("Open door button pressed! Opening the elevator doors.");
    }
    
}
