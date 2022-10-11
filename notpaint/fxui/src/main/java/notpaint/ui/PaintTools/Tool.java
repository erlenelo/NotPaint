package notpaint.ui.PaintTools;

import notpaint.core.PaintSettings;
import javafx.scene.canvas.Canvas;

public abstract class Tool {

    protected PaintSettings settings;

    public Tool(PaintSettings settings) {
        this.settings = settings;
    }

    public abstract void paint(Canvas canvas, int x, int y);
}
