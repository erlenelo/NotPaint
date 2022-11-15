module notpaint.core {
    requires transitive com.fasterxml.jackson.databind;
    requires java.net.http;


    exports notpaint.core.brushes;
    exports notpaint.persistence;
    
    // Denne brukes for å aksessere private fields i GameInfo slik at de kan serializes:
    opens notpaint.persistence to com.fasterxml.jackson.databind;
}