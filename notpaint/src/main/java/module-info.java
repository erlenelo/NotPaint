module gr2213 {
    requires javafx.controls;
    requires javafx.fxml;

    opens gr2213 to javafx.fxml;
    exports gr2213;
}
