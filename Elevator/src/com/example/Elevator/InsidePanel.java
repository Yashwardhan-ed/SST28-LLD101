package com.example.Elevator;

import java.util.ArrayList;
import java.util.List;

public class InsidePanel {
    private final List<ElevatorButton> buttons;

    public InsidePanel(int numberOfFloors) {
        this.buttons = new ArrayList<>();
        for (int i = 0; i <= numberOfFloors; i++) {
            buttons.add(new ElevatorFloorButton(i));
        }
        buttons.add(new DoorButton());
        buttons.add(new AlarmButton());
        System.out.println("InsidePanel initialized with " + buttons.size() + " buttons");
    }

    public void pressButton(int index) {
        if (index < 0 || index >= buttons.size()) {
            System.out.println("InsidePanel button index out of range: " + index);
            return;
        }
        buttons.get(index).press();
    }
}
