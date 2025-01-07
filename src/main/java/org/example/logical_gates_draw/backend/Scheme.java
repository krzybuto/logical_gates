package org.example.logical_gates_draw.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Klasa reprezentująca schemat z bramkami logicznymi.
 */
public class Scheme {

    List<Gate> gates;

    /**
     * Zwraca listę bramek logicznych w schemacie.
     * @return Lista bramek.
     */
    public List<Gate> getGates() {
        return gates;
    }

    /**
     * Konstruktor klasy, inicjalizuje pustą listę bramek.
     */
    public Scheme() {
        this.gates = new ArrayList<>();
    }

    /**
     * Zwraca liczbę bramek na danym poziomie.
     * @param level Poziom w schemacie.
     * @return Liczba bramek na poziomie.
     */
    public int GetNumberOfGatesForLevel(int level) {
        return (int)(this.gates.stream().filter(g -> g.getLevel() == level).count());
    }

    /**
     * Zwraca maksymalny poziom w schemacie.
     * @return Maksymalny poziom.
     */
    public int GetMaxLevel() {
        return this.gates.stream().map(g -> g.getLevel()).reduce((g1, g2) -> g2).get();
    }

    /**
     * Inicjalizuje bramkę z linii tekstu.
     * @param line Linia w pliku z danymi bramki.
     * @param id Identyfikator bramki.
     * @return Utworzona bramka.
     */
    private Gate InitGateFromLine(String line, int id) {
        List<String> parts = List.of(line.split(";"));
        GateType type = GateType.fromInt(Integer.parseInt(parts.get(0)));

        Gate newGate;

        switch (type) {
            case AND:
                newGate = new GateAND(id);
                break;
            case NAND:
                newGate = new GateNAND(id);
                break;
            case OR:
                newGate = new GateOR(id);
                break;
            case NOR:
                newGate = new GateNOR(id);
                break;
            case XOR:
                newGate = new GateXOR(id);
                break;
            case XNOR:
                newGate = new GateXNOR(id);
                break;
            case NOT:
                newGate = new GateNOT(id);
                break;
            default:
                newGate = new GateAND(id);
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

    /**
     * Inicjalizuje bramki na podstawie pliku.
     * @param path Ścieżka do pliku.
     */
    private void InitGatesFromFile(String path) {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int id = 0;
            while((line = br.readLine()) != null) {
                InitGateFromLine(line, id);
                id++;
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

    /**
     * Inicjalizuje bramki na podstawie łańcucha tekstowego.
     * @param gates Łańcuch tekstowy reprezentujący bramki.
     */
    public void InitGatesFromString(String gates) {
        if (!this.gates.isEmpty())
            this.gates.clear();
        int id = 0;
        List<String> lines = List.of(gates.split("\n"));
        for(String line : lines) {
            InitGateFromLine(line, id);
            id ++;
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

    /**
     * Przetwarza plik z bramkami.
     * @param filePath Ścieżka do pliku.
     * @return Wynik obliczeń po wczytaniu pliku.
     */
    public boolean ProcessFile(String filePath) {
        if (!this.gates.isEmpty())
            this.gates.clear();
        InitGatesFromFile(filePath);
        return Calculate();
    }

    /**
     * Przeprowadza obliczenia na schemacie.
     * @return Wynik obliczeń.
     */
    public boolean Calculate() {
        for (Gate gate1 : this.gates) {
            if (gate1.level != 0) {
                gate1.setInputs(new ArrayList<>());
            }
        }
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

    /**
     * Zmienia typ bramki i przelicza wynik.
     * @param gateId Identyfikator bramki.
     * @param newGateType Nowy typ bramki.
     * @return Wynik po zmianie typu.
     */
    public boolean ChangeGateTypeAndCalculate(int gateId, GateType newGateType) {
        for (int i = 0; i < gates.size(); i++) {
            Gate gate = gates.get(i);
            if (gate.getId() == gateId) {
                Gate copyGate;
                switch (newGateType) {
                    case AND:
                        copyGate = new GateAND(gateId);
                        break;
                    case NAND:
                        copyGate = new GateNAND(gateId);
                        break;
                    case OR:
                        copyGate = new GateOR(gateId);
                        break;
                    case NOR:
                        copyGate = new GateNOR(gateId);
                        break;
                    case XOR:
                        copyGate = new GateXOR(gateId);
                        break;
                    case XNOR:
                        copyGate = new GateXNOR(gateId);
                        break;
                    case NOT:
                        copyGate = new GateNOT(gateId);
                        break;
                    default:
                        copyGate = new GateAND(gateId);
                        break;
                }
                copyGate.setInputs(gate.getInputs());
                copyGate.setNext(gate.getNext());
                copyGate.setLevel(gate.getLevel());
                copyGate.setNumberInLevel(gate.getNumberInLevel());
                copyGate.setPosY(gate.getPosY());
                copyGate.setPosX(gate.getPosX());

                gates.set(i, copyGate);
                break;
            }
        }

        return Calculate();
    }

    /**
     * Zmienia wejście bramki i przelicza wynik.
     * @param gateId Identyfikator bramki.
     * @param inputId Indeks wejścia.
     * @param newInput Nowa wartość wejścia.
     * @return Wynik po zmianie wejścia.
     */
    public boolean ChangeGateInputAndCalculate(int gateId, int inputId, boolean newInput) {
        for (int i = 0; i < gates.size(); i++) {
            Gate gate = gates.get(i);
            if (gate.getId() == gateId) {
                Gate copyGate = gate.copy();
                copyGate.getInputs().set(inputId - 1, newInput);

                gates.set(i, copyGate);
                break;
            }
        }

        return Calculate();
    }
}
