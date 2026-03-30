package com.example.Elevator;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Floor> floors = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            floors.add(new Floor(i));
        }

        ElevatorManager manager = new ElevatorManager(floors, new FCFS());
        Elevator elevator1 = new Elevator(1, 1000, 5);
        Elevator elevator2 = new Elevator(2, 1200, 5);
        manager.addElevator(elevator1);
        manager.addElevator(elevator2);

        Sensor decoratedWeightSensor = new WeightAlertSensor(new CurrentFloorSensor(new WeightSensor()));
        Sensor decoratedDoorSensor = new DoorLoggingSensor(new DoorSensor());
        elevator1.addSensor(decoratedWeightSensor);
        elevator1.addSensor(decoratedDoorSensor);

        manager.handleOutsideRequest(3, Direction.UP);
        manager.handleInsideRequest(5, Direction.UP);
        elevator1.checkWeightLimit(900);
        elevator1.readSensors();

        manager.handleOutsideRequest(4, Direction.DOWN);

        EmergencyStrategy emergencyStrategy = new EmergencyStrategy();
        emergencyStrategy.handleEmergency(elevator2);
    }
}
