package com.example.Elevator;

import java.util.List;
import java.util.Map;

public class ElevatorSystem {
    private final ElevatorManager elevatorManager;

    public ElevatorSystem(Map<Integer, Elevator> elevators, List<Floor> floors, int numberOfFloors, IElevatorMoveStrategy moveStrategy) {
        this.elevatorManager = new ElevatorManager(floors, moveStrategy);
        for (Map.Entry<Integer, Elevator> entry : elevators.entrySet()) {
            this.elevatorManager.addElevator(entry.getValue());
        }
        System.out.println("ElevatorSystem initialized with " + numberOfFloors + " floors");
    }

    public void changeElevatorStatus(int elevatorId, ElevatorStatus newStatus) {
        elevatorManager.changeElevatorStatus(elevatorId, newStatus);
    }
    
    public void requestElevator(int floorNumber, Direction direction) {
        elevatorManager.handleInsideRequest(floorNumber, direction);
    }
}
