package notpaint.core.Brushes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import javafx.scene.canvas.Canvas;

public class CircleBrushTest {
    Canvas canvas;

    @Test
    public void testGetPixels() {
        CircleBrush brush = new CircleBrush(5);
        brush.getPixels(canvas, 2, 4);
        assertEquals(brush.getPixels(canvas, 2, 4).anyMatch(p -> p.getKey() == 2 && p.getValue() == 4), true);
    }
}
