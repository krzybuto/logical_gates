package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

/**
 * Klasa reprezentująca bramkę OR
 */
public class GateOR extends Gate {

    /**
     * Konstruktor, specyfikujący kształt bramki
     * @param count numer bramki
     */
    public GateOR(int count) {
        super(count);
        this.shape = new Image(getClass().getResource("/OR_2.png").toExternalForm());
    }

    /**
     * Oblicza wynik bramki na podstawie jej wejść
     * @return wynik bramki
     */
    @Override
    public boolean calculate() {
        return inputs.stream().reduce(false, (a, b) -> a || b);
    }

    /**
     * Tworzy kopię bramki
     * @return kopia bramki
     */
    @Override
    public Gate copy() {
        GateOR copy = new GateOR(this.id);
        this.copyBaseProperties(copy);
        return copy;
    }
}