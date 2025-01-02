package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Gate {
    int id;
    List<Boolean> inputs;
    List<Gate> next;
    static int count = 0;

    public Gate() {
        this.inputs = new ArrayList<>();
        this.next = new ArrayList<>();
        this.id = count++;
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
}
