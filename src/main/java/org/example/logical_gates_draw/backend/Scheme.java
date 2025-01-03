package org.example.logical_gates_draw.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Scheme {

    List<Gate> gates;

    public List<Gate> getGates() {
        return gates;
    }

    public Scheme() {
        this.gates = new ArrayList<>();
    }

    public int GetNumberOfGatesForLevel(int level) {
        return (int)(this.gates.stream().filter(g -> g.getLevel() == level).count());
    }

    public int GetMaxLevel() {
        return this.gates.stream().map(g -> g.getLevel()).reduce((g1, g2) -> g2).get();
    }

    private Gate InitGateFromLine(String line) {
        List<String> parts = List.of(line.split(";"));
        GateType type = GateType.fromInt(Integer.parseInt(parts.get(0)));

        Gate newGate;

        switch (type) {
            case AND:
                newGate = new GateAND();
                break;
            case NAND:
                newGate = new GateNAND();
                break;
            case OR:
                newGate = new GateOR();
                break;
            case NOR:
                newGate = new GateNOR();
                break;
            case XOR:
                newGate = new GateXOR();
                break;
            case XNOR:
                newGate = new GateXNOR();
                break;
            case NOT:
                newGate = new GateNOT();
                break;
            default:
                newGate = new GateAND();
                break;
        }

        List<Boolean> inputs = new ArrayList<>();
        List<Gate> nextGates = new ArrayList<>();

        newGate.setLevel(Integer.parseInt(parts.get(1)));

        if(!parts.get(2).equals("-"))
            inputs = Arrays.stream(parts.get(2).split(" "))
                    .map(i -> i.equals("1"))
                    .collect(Collectors.toList());
        if(!parts.get(3).equals("-")) {
            List<Integer> next = Arrays.stream(parts.get(3).split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            nextGates = this.gates.stream().filter(gate -> next.contains(gate.id)).collect(Collectors.toList());
        }

        newGate.setInputs(inputs);
        newGate.setNext(nextGates);

        this.gates.add(newGate);


        return newGate;
    }

    private void InitGatesFromFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = br.readLine()) != null) {
                InitGateFromLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.reverse(this.gates);

        int i = 1;
        int currentLevel = 0;
        for (Gate gate : this.gates) {
            if(gate.getLevel() > currentLevel) {
                i = 1;
                currentLevel = gate.getLevel();
            }
            gate.setNumberInLevel(i);
            i++;
        }
    }

    public boolean ProcessFile(String filePath) {
        if (!this.gates.isEmpty())
            this.gates.clear();
        InitGatesFromFile(filePath);
        return Calculate();
    }

    private boolean Calculate() {
        Optional<Boolean> result = this.gates.stream().map(gate -> {
            boolean tempResult = gate.calculate();
            for (Gate nextGate : gate.getNext()) {
                nextGate.addInput(tempResult);
            }
            return tempResult;
        }).reduce((a, b) -> b);
        if (!result.isPresent())
            throw new IllegalStateException("No result available");
        return result.get();
    }
}
