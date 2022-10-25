package notpaint.ui;

import javafx.scene.paint.Color;
import notpaint.core.brushes.Brush;

/**
 * Data class that stores data about the current paint settings.
 */
public class PaintSettings {
    Color color;
    private Brush brush;

    public Brush getBrush() {
        return brush;
    }

    public void setBrush(Brush brush) {
        this.brush = brush;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
