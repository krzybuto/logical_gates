package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

/**
 * Klasa reprezentująca bramkę NAND
 */
public class GateNAND extends GateAND {

    /**
     * Konstruktor, specyfikujący kształt bramki
     * @param count numer bramki
     */
    public GateNAND(int count) {
        super(count);
        this.shape = new Image(getClass().getResource("/NAND_2.png").toExternalForm());
    }

    /**
     * Oblicza wynik bramki na podstawie jej wejść
     * @return wynik bramki
     */
    @Override
    public boolean calculate() {
        return !super.calculate();
    }

    /**
     * Tworzy kopię bramki
     * @return kopia bramki
     */
    @Override
    public Gate copy() {
        GateNAND copy = new GateNAND(this.id);
        this.copyBaseProperties(copy);
        return copy;
    }
}