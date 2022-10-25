module notpaint.core {
    requires transitive com.fasterxml.jackson.databind;

    exports notpaint.core;
    exports notpaint.core.brushes;
    exports notpaint.core.persistence;
    
    opens notpaint.core to com.fasterxml.jackson.databind;
}