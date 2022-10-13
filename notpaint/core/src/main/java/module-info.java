module notpaint.core {
    requires transitive com.fasterxml.jackson.databind;

    exports notpaint.core;
    exports notpaint.core.Brushes;
    exports notpaint.core.Persistence;
    
    opens notpaint.core to com.fasterxml.jackson.databind;
}