package notpaint.core.brushes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link SquareBrush}.
 */
public class SquareBrushTest {

    @Test
    @DisplayName("Test if the brush is created.")
    public void testGetPixels() {
        SquareBrush brush = new SquareBrush(5);
        brush.getPixels(2, 4);
        assertEquals(brush.getPixels(2, 4).anyMatch(
            p -> p.getKey() == 2 && p.getValue() == 4), true);
    }

}
