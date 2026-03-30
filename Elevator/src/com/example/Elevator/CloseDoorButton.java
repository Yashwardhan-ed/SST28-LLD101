package com.example.Elevator;

public class CloseDoorButton extends ElevatorButton {
    @Override
    public void press() {
        System.out.println("Close door button pressed! Closing the elevator doors.");
    }
    
}
