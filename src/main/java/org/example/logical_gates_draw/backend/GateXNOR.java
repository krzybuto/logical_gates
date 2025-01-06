package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

public class GateXNOR extends GateXOR {
    public GateXNOR(int count) {
        super(count);
        this.shape = new Image(getClass().getResource("/XNOR_2.png").toExternalForm());
    }
    @Override
    public boolean calculate() {
        return !super.calculate();
    }

    @Override
    public Gate copy() {
        GateXNOR copy = new GateXNOR(this.id);
        this.copyBaseProperties(copy);
        return copy;
    }
}
