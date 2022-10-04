module notpaint.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires notpaint.core;

    opens notpaint.ui to javafx.fxml, javafx.graphics;
}
