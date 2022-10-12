package notpaint.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import notpaint.core.Brushes.Brush;
import notpaint.core.Brushes.CircleBrush;


public class PaintSettingsTest {
    @Test
    public void testGetBrush() {
        Brush brush = new Brush();
        PaintSettings paintSettings = new PaintSettings();
        paintSettings.setBrush(brush);
        assertEquals(brush, paintSettings.getBrush());

    }

    @Test
    public void testGetColor() {
        PaintSettings paintSettings = new PaintSettings();
        paintSettings.setColor(Color.BLUE);
        assertEquals(Color.BLUE, paintSettings.getColor());
        

    }

    @Test
    public void testSetBrush() {
        CircleBrush brush = new CircleBrush(5);
        PaintSettings paintSettings = new PaintSettings();
        paintSettings.setBrush(brush);
        assertEquals(paintSettings.getBrush(), paintSettings);

    }

    @Test
    public void testSetColor(Color color) {
        PaintSettings paintSettings = new PaintSettings();
        paintSettings.setColor("red");
        assertEquals(paintSettings, paintSettings.color);

    }
}
