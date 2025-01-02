module org.example.logical_gates_draw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.logical_gates_draw to javafx.fxml;
    exports org.example.logical_gates_draw;
}