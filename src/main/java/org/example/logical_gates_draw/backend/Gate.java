package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa Gate jest klasą abstrakcyjną reprezentującą bramkę logiczną.
 */
public abstract class Gate {
    /**
     * numer identyfikacyjny bramki umożliwiający tworzenie powiązań między kolejnymi bramkami
     */
    int id;
    /**
     * dane wejściowe dla danej bramki
     */
    List<Boolean> inputs;
    /**
     * bramki, do których dana bramka ma przekazać wynik swojego wykonania jako daną wejściową
     */
    List<Gate> next;
    /**
     * graficzna reprezentacja bramki
     */
    Image shape;
    /**
     * zmienne opisujące rozmiary reprezentacji graficznej
     */
    public final static int sizeX = 60;
    public final static int sizeY = 60;
    /**
     * poziom bramki w schemacie
     */
    int level;
    /**
     * numer bramki w schemacie w obrębie poziomu
     */
    int numberInLevel;
    /**
     * zmienne opisujące położenie graficznej reprezentacji bramki na scenie
     */
    int posX;
    int posY;

    /**
     * Konstruktor bramki
     * @param count to numer bramki w pliku ze schematem
     */
    public Gate(int count) {
        this.inputs = new ArrayList<>();
        this.next = new ArrayList<>();
        this.id = count;
        this.level = 0;
        this.shape = new Image(getClass().getResource("/NULL.png").toExternalForm());
    }

    /**
     * Pobranie id bramki
     * @return id bramki
     */
    public int getId() {
        return id;
    }

    /**
     * Ustala pozycję X bramki na scenie.
     * @param posX Pozycja X bramki.
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Ustala pozycję Y bramki na scenie.
     * @param posY Pozycja Y bramki.
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Pobiera pozycję X bramki na scenie.
     * @return Pozycja X bramki.
     */
    public int getPosX(){
        return posX;
    }

    /**
     * Pobiera pozycję Y bramki na scenie.
     * @return Pozycja Y bramki.
     */
    public int getPosY(){
        return posY;
    }

    /**
     * Pobiera numer bramki w obrębie poziomu.
     * @return Numer bramki w poziomie.
     */
    public int getNumberInLevel() {
        return numberInLevel;
    }

    /**
     * Ustala numer bramki w obrębie poziomu.
     * @param numberInLevel Numer bramki w poziomie.
     */
    public void setNumberInLevel(int numberInLevel) {
        this.numberInLevel = numberInLevel;
    }

    /**
     * Ustala poziom bramki w schemacie.
     * @param level Poziom bramki.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Pobiera poziom bramki w schemacie.
     * @return Poziom bramki.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Pobiera dane wejściowe bramki.
     * @return Lista wejściowych danych bramki.
     */
    public List<Boolean> getInputs() {
        return inputs;
    }

    /**
     * Ustala dane wejściowe bramki.
     * @param inputs Lista wejściowych danych bramki.
     */
    public void setInputs(List<Boolean> inputs) {
        this.inputs = inputs;
    }

    /**
     * Dodaje jedno wejście do bramki.
     * @param input Wartość wejścia (true lub false).
     */
    public void addInput(boolean input) {
        this.inputs.add(input);
    }

    /**
     * Pobiera listę bramek następnych, do których przekazywany jest wynik obliczeń bramki.
     * @return Lista następnych bramek.
     */
    public List<Gate> getNext() {
        return next;
    }

    /**
     * Ustala listę bramek następnych, do których przekazywany jest wynik obliczeń bramki.
     * @param next Lista bramek następnych.
     */
    public void setNext(List<Gate> next) {
        this.next = next;
    }

    /**
     * Metoda obliczająca wynik bramki.
     * @return Wynik obliczeń bramki.
     */
    public abstract boolean calculate();

    /**
     * Rysowanie bramki.
     * @return Graficzna reprezentacja bramki.
     */
    public Image draw() {
        return this.shape;
    }

    /**
     * Tworzenie kopii bramki.
     * @return Kopia bramki.
     */
    public abstract Gate copy();

    /**
     * Kopiuje podstawowe właściwości bramki do innej bramki.
     * @param target Obiekt, do którego kopiowane są właściwości.
     */
    protected void copyBaseProperties(Gate target) {
        target.setInputs(new ArrayList<>(this.inputs)); // Głębokie kopiowanie wejść
        target.setNext(new ArrayList<>(this.next)); // Skopiowanie następników (ale pozostają referencje)
        target.setLevel(this.level);
        target.setNumberInLevel(this.numberInLevel);
        target.setPosX(this.posX);
        target.setPosY(this.posY);
    }
}
