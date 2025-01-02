package org.example;

public class GateNOT extends Gate{

    @Override
    public boolean calculate() {
        if (inputs.size() > 1)
            throw new IllegalArgumentException("Input too large");
        else
            return !inputs.get(0);
    }
}
