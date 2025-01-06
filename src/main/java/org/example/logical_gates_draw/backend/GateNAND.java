package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

public class GateNAND extends GateAND {

    public GateNAND(int count) {
        super(count);
        this.shape = new Image(getClass().getResource("/NAND_2.png").toExternalForm());
    }

    @Override
    public boolean calculate() {
        return !super.calculate();
    }

    @Override
    public Gate copy() {
        GateNAND copy = new GateNAND(this.id);
        this.copyBaseProperties(copy);
        return copy;
    }
}