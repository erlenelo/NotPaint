module notpaint.core {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports notpaint.core;
    exports notpaint.core.Brushes;
    exports notpaint.core.Persistence;
}