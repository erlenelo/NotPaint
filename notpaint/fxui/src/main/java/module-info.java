module notpaint.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.net.http;
    
    requires notpaint.core;
    
    opens notpaint.ui to javafx.fxml, javafx.graphics, javafx.base;
}
