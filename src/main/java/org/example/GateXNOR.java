package org.example;

public class GateXNOR extends GateXOR{

    @Override
    public boolean calculate() {
        return !super.calculate();
    }
}
