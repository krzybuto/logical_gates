package org.example.logical_gates_draw.backend;

import javafx.scene.image.Image;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Gate {
    int id;
    List<Boolean> inputs;
    List<Gate> next;
    Image shape;
    static int count = 0;
    public final static int sizeX = 60;
    public final static int sizeY = 60;
    int level;
    int numberInLevel;

    int posX;
    int posY;

    public Gate() {
        this.inputs = new ArrayList<>();
        this.next = new ArrayList<>();
        this.id = count++;
        this.level = 0;
        this.shape = new Image(getClass().getResource("/AND.png").toExternalForm());
    }

    public int getPosX(){
        posX = 100 + level * (Gate.sizeX+60);
        return posX;
    }

    public int getPosY(){
        posY = 100 + numberInLevel * (Gate.sizeY+30);
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
}
