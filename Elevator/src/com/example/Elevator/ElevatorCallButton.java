package com.example.Elevator;

public class ElevatorCallButton extends ElevatorButton {
    private final int floorNumber;
    private final Direction direction;

    @Override
    public void press() {
        System.out.println("Elevator call button pressed at floor " + floorNumber + " for direction " + direction);
    }
    
    public ElevatorCallButton(int floorNumber, Direction direction) {
        this.floorNumber = floorNumber;
        this.direction = direction;
    }
    
    public int getFloorNumber() {
        return floorNumber;
    }
    
    public Direction getDirection() {
        return direction;
    }
}
