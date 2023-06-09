package notpaint.core.brushes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link CircleBrush}.
 */
public class CircleBrushTest {

    @Test
    public void testGetPixels() {
        CircleBrush brush = new CircleBrush(5);
        brush.getPixels(2, 4);
        assertEquals(brush.getPixels(2, 4).anyMatch(
            p -> p.getKey() == 2 && p.getValue() == 4), true);
    }
}
