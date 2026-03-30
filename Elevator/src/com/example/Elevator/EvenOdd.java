package com.example.Elevator;

import java.util.Map;

public class EvenOdd implements IElevatorMoveStrategy {
    @Override
    public Elevator selectElevator(Map<Integer, Elevator> elevators, int targetFloor, Direction direction) {
        System.out.println("EvenOdd strategy evaluating request for floor " + targetFloor + " direction " + direction);
        for (Elevator elevator : elevators.values()) {
            if (targetFloor % 2 == 0 && elevator.getElevatorId() % 2 == 0) {
                System.out.println("EvenOdd selected even elevator " + elevator.getElevatorId());
                return elevator;
            }
            if (targetFloor % 2 != 0 && elevator.getElevatorId() % 2 != 0) {
                System.out.println("EvenOdd selected odd elevator " + elevator.getElevatorId());
                return elevator;
            }
        }
        for (Elevator elevator : elevators.values()) {
            System.out.println("EvenOdd fallback selected elevator " + elevator.getElevatorId());
            return elevator;
        }
        System.out.println("EvenOdd could not find an elevator");
        return null;
    }
}
