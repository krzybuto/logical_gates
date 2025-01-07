package org.example.logical_gates_draw;

/**
 * Enum reprezentujący typy zadań w grze związane z bramkami logicznymi.
 *
 * Enum zawiera trzy typy zadań:
 * - OUTPUT: Zadanie dotyczące wyjścia układu logicznego.
 * - INPUT: Zadanie dotyczące wejścia układu logicznego.
 * - GATE: Zadanie związane z bramkami logicznymi.
 */
public enum TaskType {
    OUTPUT(0),
    INPUT(1),
    GATE(2);

    /**
     * Kod dla danego typu zadania.
     */
    private final int code;

    /**
     * Konstruktor enum, przypisuje kod dla danego typu zadania.
     *
     * @param code Kod typu zadania.
     */
    TaskType(int code) {
        this.code = code;
    }

    /**
     * Metoda statyczna, która konwertuje liczbę całkowitą na odpowiadający jej typ zadania.
     *
     * @param code Kod typu zadania.
     * @return Odpowiadający typ zadania.
     * @throws IllegalArgumentException Jeśli kod nie pasuje do żadnego typu zadania.
     */
    public static TaskType fromInt(int code) {
        for (TaskType status : TaskType.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nieznany kod statusu: " + code);
    }
}
