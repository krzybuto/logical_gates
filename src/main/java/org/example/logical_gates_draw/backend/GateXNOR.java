package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

/**
 * Klasa reprezentująca bramkę XNOR
 */
public class GateXNOR extends GateXOR {

    /**
     * Konstruktor, specyfikujący kształt bramki
     * @param count numer bramki
     */
    public GateXNOR(int count) {
        super(count);
        this.shape = new Image(getClass().getResource("/XNOR_2.png").toExternalForm());
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
        GateXNOR copy = new GateXNOR(this.id);
        this.copyBaseProperties(copy);
        return copy;
    }
}
