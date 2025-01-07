package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class Gate {
    int id;
    List<Boolean> inputs;
    List<Gate> next;
    Image shape;
    public final static int sizeX = 60;
    public final static int sizeY = 60;
    int level;
    int numberInLevel;

    int posX;
    int posY;

    public Gate(int count) {
        this.inputs = new ArrayList<>();
        this.next = new ArrayList<>();
        this.id = count;
        this.level = 0;
        this.shape = new Image(getClass().getResource("/NULL.png").toExternalForm());
    }

    public int getId() {
        return id;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public int getNumberInLevel() {
        return numberInLevel;
    }

    public void setNumberInLevel(int numberInLevel) {
        this.numberInLevel = numberInLevel;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public List<Boolean> getInputs() {
        return inputs;
    }

    public void setInputs(List<Boolean> inputs) {
        this.inputs = inputs;
    }

    public void addInput(boolean input) {
        this.inputs.add(input);
    }

    public List<Gate> getNext() {
        return next;
    }

    public void setNext(List<Gate> next) {
        this.next = next;
    }

    public abstract boolean calculate();

    public Image draw() {
        return this.shape;
    }

    public Image obstruct() {
        return new Image(getClass().getResource("/NULL.png").toExternalForm());
    }

    public abstract Gate copy();

    protected void copyBaseProperties(Gate target) {
        target.setInputs(new ArrayList<>(this.inputs)); // Głębokie kopiowanie wejść
        target.setNext(new ArrayList<>(this.next)); // Skopiowanie następników (ale pozostają referencje)
        target.setLevel(this.level);
        target.setNumberInLevel(this.numberInLevel);
        target.setPosX(this.posX);
        target.setPosY(this.posY);
    }
}
