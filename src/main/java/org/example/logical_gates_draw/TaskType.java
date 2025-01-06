package org.example.logical_gates_draw;

import org.example.logical_gates_draw.backend.GateType;

public enum TaskType {
    OUTPUT(0),
    INPUT(1),
    GATE(2);

    private final int code;

    TaskType(int code) {
        this.code = code;
    }

    public static TaskType fromInt(int code) {
        for (TaskType status : TaskType.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nieznany kod statusu: " + code);
    }
}
