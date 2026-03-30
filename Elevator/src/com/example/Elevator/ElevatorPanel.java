package com.example.Elevator;

public class ElevatorPanel extends Panel {

    public ElevatorPanel(int numberOfFloors) {
        // Initialize floor buttons, alarm button, open/close door buttons
        super(numberOfFloors);
        // Add floor buttons (for example, for 10 floors)
        for (int i = 1; i <= numberOfFloors; i++) {
            buttons.add(new ElevatorFloorButton(i));
        }
        buttons.add(new AlarmButton());
        buttons.add(new OpenDoorButton());
        buttons.add(new CloseDoorButton());
        System.out.println("ElevatorPanel initialized with " + buttons.size() + " buttons");
    }
}
