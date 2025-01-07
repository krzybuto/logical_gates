package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

/**
 * Klasa reprezentująca bramkę NOR
 */
public class GateNOR extends GateOR {

    /**
     * Konstruktor, specyfikujący kształt bramki
     * @param count numer bramki
     */
    public GateNOR(int count) {
        super(count);
        this.shape = new Image(getClass().getResource("/NOR_2.png").toExternalForm());
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
        GateNOR copy = new GateNOR(this.id);
        this.copyBaseProperties(copy);
        return copy;
    }
}
