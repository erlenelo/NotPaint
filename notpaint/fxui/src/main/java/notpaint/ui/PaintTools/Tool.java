package notpaint.ui.PaintTools;

import javafx.scene.canvas.Canvas;
import notpaint.ui.PaintSettings;

public abstract class Tool {

    protected PaintSettings settings;

    public Tool(PaintSettings settings) {
        this.settings = settings;
    }

    public abstract void paint(Canvas canvas, int x, int y);
}
