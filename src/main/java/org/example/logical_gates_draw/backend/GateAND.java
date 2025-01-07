package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

/**
 * Klasa reprezentująca bramkę AND
 */
public class GateAND extends Gate {

    /**
     * Konstruktor, specyfikujący kształt bramki
     * @param count numer bramki
     */
    public GateAND(int count) {
        super(count);
        this.shape = new Image(getClass().getResource("/AND_2.png").toExternalForm());
    }

    /**
     * Oblicza wynik bramki na podstawie jej wejść
     * @return wynik bramki
     */
    @Override
    public boolean calculate() {
        return inputs.stream().reduce(true, (a, b) -> a && b);
    }

    /**
     * Tworzy kopię bramki
     * @return kopia bramki
     */
    @Override
    public Gate copy() {
        GateAND copy = new GateAND(this.id);
        this.copyBaseProperties(copy);
        return copy;
    }
}