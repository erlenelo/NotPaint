package notpaint.core;

import gr2213.Brushes.Brush;
import javafx.scene.paint.Color;

public class PaintSettings {
    private Color color;
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
