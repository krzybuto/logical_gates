package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

public class GateNAND extends GateAND {

    public GateNAND() {
        this.shape = new Image(getClass().getResource("/NAND.png").toExternalForm());
    }

    @Override
    public boolean calculate() {
        return !super.calculate();
    }

}