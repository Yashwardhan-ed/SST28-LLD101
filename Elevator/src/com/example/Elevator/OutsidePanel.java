package com.example.Elevator;

import java.util.ArrayList;
import java.util.List;

public class OutsidePanel {
    private final List<ElevatorRequestButton> requestButtons;

    public OutsidePanel() {
        this.requestButtons = new ArrayList<>();
        requestButtons.add(new ElevatorRequestButton(Direction.UP));
        requestButtons.add(new ElevatorRequestButton(Direction.DOWN));
        System.out.println("OutsidePanel initialized");
    }

    public void pressButton(int index) {
        if (index < 0 || index >= requestButtons.size()) {
            System.out.println("OutsidePanel button index out of range: " + index);
            return;
        }
        requestButtons.get(index).press();
    }
}
