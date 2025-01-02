package org.example;

public class GateXOR extends Gate{

    @Override
    public boolean calculate() {
        return inputs.stream().reduce(false, (a, b) -> a ^ b);
    }
}
