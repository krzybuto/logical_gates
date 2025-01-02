package org.example;

public class GateNAND extends GateAND {

    @Override
    public boolean calculate() {
        return !super.calculate();
    }
}