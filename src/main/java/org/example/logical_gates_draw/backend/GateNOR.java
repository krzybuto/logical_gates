package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

public class GateNOR extends GateOR {

    public GateNOR() {
        this.shape = new Image(getClass().getResource("/NOR.png").toExternalForm());
    }
    @Override
    public boolean calculate() {
        return !super.calculate();
    }
}
