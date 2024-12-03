package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GateOR {
    public static void main(String[] args) {
        try {
            // Wczytaj plik wejściowy
            File file = new File("C:\\Users\\Jacek\\IdeaProjects\\logical_gates\\src\\main\\resources\\input");
            Scanner fileScanner = new Scanner(file);

            // Pobierz pierwsze i drugie wejście z pliku
            if (fileScanner.hasNextInt()) {
                int input1 = fileScanner.nextInt();
                if (fileScanner.hasNextInt()) {
                    int input2 = fileScanner.nextInt();

                    // Walidacja wejść
                    if ((input1 != 0 && input1 != 1) || (input2 != 0 && input2 != 1)) {
                        System.out.println("Błędne dane wejściowe w pliku! Podaj wartości 0 lub 1.");
                    } else {
                        // Oblicz wynik bramki OR
                        int output = input1 | input2; // Operacja OR na bitach
                        System.out.println("Wyjście bramki OR: " + output);
                    }
                } else {
                    System.out.println("Brak drugiego wejścia w pliku!");
                }
            } else {
                System.out.println("Brak danych w pliku lub niepoprawne dane!");
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Plik wejściowy nie został znaleziony!");
        }
    }
}