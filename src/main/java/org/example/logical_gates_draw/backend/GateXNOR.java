package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

public class GateXNOR extends GateXOR {
    public GateXNOR() {
        this.shape = new Image(getClass().getResource("/XNOR.png").toExternalForm());
    }
    @Override
    public boolean calculate() {
        return !super.calculate();
    }
}
