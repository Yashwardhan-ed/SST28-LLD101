package com.example.Elevator;

import java.util.Map;

public interface IElevatorMoveStrategy {
    Elevator selectElevator(Map<Integer, Elevator> elevators, int targetFloor, Direction direction);
}
