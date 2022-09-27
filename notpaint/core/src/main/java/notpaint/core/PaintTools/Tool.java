package notpaint.core.PaintTools;

import gr2213.PaintSettings;
import javafx.scene.canvas.Canvas;

public abstract class Tool {

    protected PaintSettings settings;

    public Tool(PaintSettings settings) {
        this.settings = settings;
    }

    public abstract void Paint(Canvas canvas, int x, int y);
}
