package com.example.Elevator;

public class ElevatorFloorButton extends ElevatorButton {
    private final int floorNumber;

    public ElevatorFloorButton(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public void press() {
        System.out.println("Elevator floor button pressed for floor " + floorNumber);
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
