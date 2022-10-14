package notpaint.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import notpaint.core.Brushes.Brush;
import notpaint.core.Brushes.CircleBrush;
import notpaint.core.Brushes.SquareBrush;

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
