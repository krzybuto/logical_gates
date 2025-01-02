package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GateOR extends Gate {

    @Override
    public boolean calculate() {
        return inputs.stream().reduce(false, (a, b) -> a || b);
    }
}