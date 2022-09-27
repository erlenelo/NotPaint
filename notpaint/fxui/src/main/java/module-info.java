module notpaint.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    
    opens notpaint.ui to javafx.fxml;
}
