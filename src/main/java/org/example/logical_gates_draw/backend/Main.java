package org.example.logical_gates_draw.backend;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scheme scheme = new Scheme();
        System.out.println(scheme.ProcessFile("input1.txt") ? 1 : 0);
    }
}