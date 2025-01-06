package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

public class GateXOR extends Gate{
    public GateXOR(int count) {
        super(count);
        this.shape = new Image(getClass().getResource("/XOR_2.png").toExternalForm());
    }
    @Override
    public boolean calculate() {
        return inputs.stream().reduce(false, (a, b) -> a ^ b);
    }

    @Override
    public Gate copy() {
        GateXOR copy = new GateXOR(this.id);
        this.copyBaseProperties(copy);
        return copy;
    }
}
