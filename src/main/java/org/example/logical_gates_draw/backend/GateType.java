package org.example.logical_gates_draw.backend;

/**
 * Enum reprezentujący typy bramek logicznych.
 */
public enum GateType {
    AND(0),
    OR(1),
    NOT(2),
    XOR(3),
    NAND(4),
    NOR(5),
    XNOR(6);

    /**
     * Kod numeryczny typu bramki.
     */
    private final int code;

    /**
     * Konstruktor dla typu bramki.
     * @param code Kod numeryczny typu bramki.
     */
    GateType(int code) {
        this.code = code;
    }

    /**
     * Zwraca typ bramki na podstawie kodu numerycznego.
     * @param code Kod numeryczny typu bramki.
     * @return Typ bramki odpowiadający kodowi.
     * @throws IllegalArgumentException Jeśli kod nie pasuje do żadnego typu.
     */
    public static GateType fromInt(int code) {
        for (GateType status : GateType.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nieznany kod statusu: " + code);
    }

    /**
     * Zwraca typ bramki na podstawie nazwy w postaci tekstowej.
     * @param code Nazwa typu bramki.
     * @return Typ bramki odpowiadający nazwie.
     * @throws IllegalArgumentException Jeśli nazwa nie pasuje do żadnego typu.
     */
    public static GateType fromString(String code) {
        for (GateType status : GateType.values()) {
            if (status.toString().equals(code.toUpperCase())) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nieznany kod statusu: " + code);
    }
}
