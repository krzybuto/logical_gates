package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

public class GateNOT extends Gate{

    public GateNOT(){
        this.shape = new Image(getClass().getResource("/NOT_2.png").toExternalForm());
    }
    @Override
    public boolean calculate() {
        if (inputs.size() > 1)
            throw new IllegalArgumentException("Input too large");
        else
            return !inputs.get(0);
    }
}
