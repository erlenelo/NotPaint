module notpaint.core {
    requires transitive com.fasterxml.jackson.databind;
    requires java.net.http;

    exports notpaint.core;
    exports notpaint.core.brushes;
    exports notpaint.core.persistence;
    
    opens notpaint.core to com.fasterxml.jackson.databind;
}