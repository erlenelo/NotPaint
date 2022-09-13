package gr2213.PaintTools;

import gr2213.PaintSettings;
import javafx.scene.canvas.Canvas;

public abstract class PaintTool {

    protected PaintSettings settings;

    public PaintTool(PaintSettings settings) {
        this.settings = settings;
    }

    public abstract void Paint(Canvas canvas, int x, int y);
}
