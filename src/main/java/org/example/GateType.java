package org.example;

public enum GateType {
    AND(0),
    OR(1),
    NOT(2),
    XOR(3),
    NAND(4),
    NOR(5),
    XNOR(6);

    private final int code;

    GateType(int code) {
        this.code = code;
    }

    public static GateType fromInt(int code) {
        for (GateType status : GateType.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nieznany kod statusu: " + code);
    }
}
