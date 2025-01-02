package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GateAND extends Gate {

    @Override
    public boolean calculate() {
        return inputs.stream().reduce(true, (a, b) -> a && b);
    }
}