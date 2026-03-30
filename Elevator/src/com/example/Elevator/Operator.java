package com.example.Elevator;

public class Operator {
    private final ElevatorManager elevatorManager;

    public Operator(ElevatorManager elevatorManager) {
        this.elevatorManager = elevatorManager;
    }

    public void addFloor(int floorNumber) {
        System.out.println("Operator requested to add floor " + floorNumber + " (placeholder only)");
    }

    public void changeStatus(int elevatorId, ElevatorStatus status) {
        elevatorManager.changeElevatorStatus(elevatorId, status);
    }
}
