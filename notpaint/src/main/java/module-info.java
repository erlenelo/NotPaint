module gr2213 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    
    opens gr2213 to javafx.fxml;
    exports gr2213;
}
