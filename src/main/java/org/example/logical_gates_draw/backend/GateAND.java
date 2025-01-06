package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

public class GateAND extends Gate {

    public GateAND(int count) {
        super(count);
        this.shape = new Image(getClass().getResource("/AND_2.png").toExternalForm());
    }

    @Override
    public boolean calculate() {
        return inputs.stream().reduce(true, (a, b) -> a && b);
    }

    @Override
    public Gate copy() {
        GateAND copy = new GateAND(this.id);
        this.copyBaseProperties(copy);
        return copy;
    }
}