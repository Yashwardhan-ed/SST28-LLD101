package com.example.Elevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElevatorManager {
    private final List<Elevator> elevators;
    private final List<Floor> floors;
    private IElevatorMoveStrategy moveStrategy;

    public ElevatorManager(List<Floor> floors, IElevatorMoveStrategy moveStrategy) {
        this.elevators = new ArrayList<>();
        this.floors = floors;
        this.moveStrategy = moveStrategy;
        System.out.println("ElevatorManager initialized");
    }

    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
        System.out.println("ElevatorManager added elevator " + elevator.getElevatorId());
    }

    public void changeElevatorStatus(int elevatorId, ElevatorStatus newStatus) {
        for (Elevator elevator : elevators) {
            if (elevator.getElevatorId() == elevatorId) {
                elevator.setStatus(newStatus);
                return;
            }
        }
        System.out.println("No elevator found for id " + elevatorId);
    }

    public void handleOutsideRequest(int floorNumber, Direction direction) {
        System.out.println("Handling outside request for floor " + floorNumber + " direction " + direction);
        Elevator selected = moveStrategy.selectElevator(getElevatorMap(), floorNumber, direction);
        if (selected == null) {
            System.out.println("No elevator available for outside request");
            return;
        }
        selected.addRequest(floorNumber);
        selected.move();
    }

    public void handleInsideRequest(int floorNumber, Direction direction) {
        System.out.println("Handling inside request for floor " + floorNumber + " direction " + direction);
        Elevator selected = moveStrategy.selectElevator(getElevatorMap(), floorNumber, direction);
        if (selected == null) {
            System.out.println("No elevator available for inside request");
            return;
        }
        selected.addRequest(floorNumber);
        selected.move();
    }

    private Map<Integer, Elevator> getElevatorMap() {
        Map<Integer, Elevator> elevatorMap = new HashMap<>();
        for (Elevator elevator : elevators) {
            elevatorMap.put(elevator.getElevatorId(), elevator);
        }
        return elevatorMap;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
