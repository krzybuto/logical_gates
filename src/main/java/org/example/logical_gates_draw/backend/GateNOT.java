package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

/**
 * Klasa reprezentująca bramkę NOT
 */
public class GateNOT extends Gate{

    /**
     * Konstruktor, specyfikujący kształt bramki
     * @param count numer bramki
     */
    public GateNOT(int count) {
        super(count);
        this.shape = new Image(getClass().getResource("/NOT_2.png").toExternalForm());
    }

    /**
     * Oblicza wynik bramki na podstawie jej wejść
     * @return wynik bramki
     */
    @Override
    public boolean calculate() {
        if (inputs.size() > 1)
            throw new IllegalArgumentException("Input too large");
        else
            return !inputs.get(0);
    }

    /**
     * Tworzy kopię bramki
     * @return kopia bramki
     */
    @Override
    public Gate copy() {
        GateNOT copy = new GateNOT(this.id);
        this.copyBaseProperties(copy);
        return copy;
    }
}
