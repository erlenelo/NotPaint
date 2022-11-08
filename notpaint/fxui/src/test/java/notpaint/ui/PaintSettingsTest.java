package notpaint.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javafx.scene.paint.Color;
import notpaint.core.brushes.Brush;
import notpaint.core.brushes.CircleBrush;
import notpaint.core.brushes.SquareBrush;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link PaintSettings}.
 */
public class PaintSettingsTest {
    @Test
    public void testGetBrush() {
        SquareBrush brush = new SquareBrush(5);
        PaintSettings paintSettings = new PaintSettings();
        paintSettings.setBrush(brush);
        Brush test = paintSettings.getBrush();
        assertEquals(test.size, 5);

    }

    @Test
    public void testGetColor() {
        PaintSettings paintSettings = new PaintSettings();
        paintSettings.setColor(Color.BLUE);
        Color test = paintSettings.getColor();
        assertNotNull(test);
    }

    @Test
    public void testSetBrush() {
        CircleBrush brush = new CircleBrush(5);
        PaintSettings paintSettings = new PaintSettings();
        paintSettings.setBrush(brush);
        assertEquals(brush.size, 5);

    }

    @Test
    public void testSetColor() {
        PaintSettings paintSettings = new PaintSettings();
        paintSettings.setColor(Color.BLACK);
        paintSettings.getColor();
        assertNotNull(paintSettings.color);

    }
}
