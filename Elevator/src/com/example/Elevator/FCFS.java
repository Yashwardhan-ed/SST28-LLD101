package com.example.Elevator;

import java.util.Map;

public class FCFS implements IElevatorMoveStrategy {
    @Override
    public Elevator selectElevator(Map<Integer, Elevator> elevators, int targetFloor, Direction direction) {
        System.out.println("FCFS strategy evaluating request for floor " + targetFloor + " direction " + direction);
        for (Elevator elevator : elevators.values()) {
            System.out.println("FCFS selected elevator " + elevator.getElevatorId());
            return elevator;
        }
        System.out.println("FCFS could not find an elevator");
        return null;
    }
}
