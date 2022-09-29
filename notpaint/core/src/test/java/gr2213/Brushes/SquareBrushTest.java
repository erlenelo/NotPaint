package notpaint.core.Brushes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javafx.scene.canvas.Canvas;

public class SquareBrushTest {

    Canvas canvas;

    @Test
    @DisplayName("Test if the brush is created.")
    public void testGetPixels() {
        SquareBrush brush = new SquareBrush(5);
        brush.GetPixels(canvas, 2, 4);
        assertEquals(brush.GetPixels(canvas, 2, 4).anyMatch(p -> p.getKey() == 2 && p.getValue() == 4), true);
    }

}
