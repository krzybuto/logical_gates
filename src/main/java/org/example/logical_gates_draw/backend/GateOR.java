package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

public class GateOR extends Gate {

    public GateOR(int count) {
        super(count);
        this.shape = new Image(getClass().getResource("/OR_2.png").toExternalForm());
    }

    @Override
    public boolean calculate() {
        return inputs.stream().reduce(false, (a, b) -> a || b);
    }

    @Override
    public Gate copy() {
        GateOR copy = new GateOR(this.id);
        this.copyBaseProperties(copy);
        return copy;
    }
}