package notpaint.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

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

    /* @Test
    public void testGetColor() {
        Color color = Color.BLACK;
        PaintSettings paintSettings = new PaintSettings();
        paintSettings.setColor(Color.BLUE);
        Color test = paintSettings.getColor();
        assertEquals(test, 5)
    }*/
        

    

    @Test
    public void testSetBrush() {
        CircleBrush brush = new CircleBrush(5);
        PaintSettings paintSettings = new PaintSettings();
        paintSettings.setBrush(brush);
        assertEquals(brush.size, 5);

    }

    /* @Test
    public void testSetColor(Color color) {
        PaintSettings paintSettings = new PaintSettings();

        paintSettings.setColor(Color.BLACK);
        

    } */
}
