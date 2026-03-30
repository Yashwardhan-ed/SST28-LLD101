package com.example.Elevator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Elevator {
    private final int elevatorId;
    private final int maxWeight;
    private int currentWeight;
    private final InsidePanel insidePanel;
    private ElevatorStatus status;
    private int currentFloor;
    private IElevatorMoveStrategy moveStrategy;
    private final Queue<Integer> requestQueue;
    private final List<Sensor> sensors;

    public Elevator(int elevatorId, int maxWeight, int numberOfFloors) {
        this.elevatorId = elevatorId;
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
        this.status = ElevatorStatus.IDLE;
        this.insidePanel = new InsidePanel(numberOfFloors);
        this.currentFloor = 0;
        this.requestQueue = new LinkedList<>();
        this.sensors = new ArrayList<>();
        System.out.println("Elevator created -> id=" + elevatorId + ", maxWeight=" + maxWeight);
    }

    public void moveToFloor(int floorNumber) {
        status = floorNumber >= currentFloor ? ElevatorStatus.MOVING_UP : ElevatorStatus.MOVING_DOWN;
        System.out.println("Elevator " + elevatorId + " moving from floor " + currentFloor + " to " + floorNumber);
        currentFloor = floorNumber;
        status = ElevatorStatus.IDLE;
        System.out.println("Elevator " + elevatorId + " has reached floor " + floorNumber);
    }

    public void addRequest(int floorNumber) {
        requestQueue.offer(floorNumber);
        System.out.println("Request queued in elevator " + elevatorId + " for floor " + floorNumber);
    }

    public void checkWeightLimit(int weight) {
        this.currentWeight = weight;
        if (currentWeight > maxWeight) {
            System.out.println("Weight limit exceeded in elevator " + elevatorId + "!");
        } else {
            System.out.println("Current weight is safe in elevator " + elevatorId);
        }
    }

    public void updateFloor(int floorNumber) {
        this.currentFloor = floorNumber;
        System.out.println("Elevator " + elevatorId + " floor updated to " + floorNumber);
    }

    public void move() {
        Integer nextFloor = requestQueue.poll();
        if (nextFloor == null) {
            System.out.println("Elevator " + elevatorId + " has no pending requests");
            return;
        }
        moveToFloor(nextFloor);
    }

    public void addSensor(Sensor sensor) {
        this.sensors.add(sensor);
        System.out.println("Sensor added to elevator " + elevatorId + ": " + sensor.getClass().getSimpleName());
    }

    public void readSensors() {
        for (Sensor sensor : sensors) {
            System.out.println(sensor.readSensor());
        }
    }

    public void setStatus(ElevatorStatus newStatus) {
        this.status = newStatus;
        System.out.println("Elevator " + elevatorId + " status changed to " + newStatus);
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    public void setMoveStrategy(IElevatorMoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
        System.out.println("Move strategy set for elevator " + elevatorId + " -> " + moveStrategy.getClass().getSimpleName());
    }

    public InsidePanel getInsidePanel() {
        return insidePanel;
    }
}

