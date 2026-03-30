package com.example.Elevator;

import java.util.ArrayList;
import java.util.List;

public abstract class Panel {
    protected List<ElevatorButton> buttons;

    public Panel(int numberOfFloors) {
        // Initialize the list of buttons
        buttons = new ArrayList<>();
    }

    public void pressButton(int buttonIndex) {
        if (buttonIndex >= 0 && buttonIndex < buttons.size()) {
            buttons.get(buttonIndex).press();
        } else {
            System.out.println("Invalid button index!");
        }
    }
}
