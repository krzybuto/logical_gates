package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

public class GateAND extends Gate {

    public GateAND() {
        this.shape = new Image(getClass().getResource("/AND.png").toExternalForm());
    }

    @Override
    public boolean calculate() {
        return inputs.stream().reduce(true, (a, b) -> a && b);
    }
}