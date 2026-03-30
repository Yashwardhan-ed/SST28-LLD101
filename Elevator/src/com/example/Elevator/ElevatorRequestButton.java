package com.example.Elevator;

public class ElevatorRequestButton {
    private final Direction direction;

    public ElevatorRequestButton(Direction direction) {
        this.direction = direction;
    }

    public void press() {
        System.out.println("Outside elevator request button pressed for direction " + direction);
    }
}
