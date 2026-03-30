package com.example.Elevator;

public class DoorButton extends ElevatorButton {
    @Override
    public void press() {
        System.out.println("Door button pressed inside elevator");
    }
}
