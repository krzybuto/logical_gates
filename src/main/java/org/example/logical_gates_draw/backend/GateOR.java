package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

public class GateOR extends Gate {

    public GateOR() {
        this.shape = new Image(getClass().getResource("/OR.png").toExternalForm());
    }

    @Override
    public boolean calculate() {
        return inputs.stream().reduce(false, (a, b) -> a || b);
    }
}