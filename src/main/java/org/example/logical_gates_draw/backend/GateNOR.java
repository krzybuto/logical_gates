package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

public class GateNOR extends GateOR {

    public GateNOR(int count) {
        super(count);
        this.shape = new Image(getClass().getResource("/NOR_2.png").toExternalForm());
    }
    @Override
    public boolean calculate() {
        return !super.calculate();
    }

    @Override
    public Gate copy() {
        GateNOR copy = new GateNOR(this.id);
        this.copyBaseProperties(copy);
        return copy;
    }
}
