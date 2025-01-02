package org.example;

public class GateNOR extends GateOR {

    @Override
    public boolean calculate() {
        return !super.calculate();
    }
}
