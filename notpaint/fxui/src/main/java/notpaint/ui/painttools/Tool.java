package notpaint.ui.painttools;

import javafx.scene.canvas.Canvas;
import notpaint.ui.PaintSettings;

/**
 * Abstract class that represents a tool that can be used to manipulate the
 * canvas.
 */
public abstract class Tool {

    protected PaintSettings settings;

    public Tool(PaintSettings settings) {
        this.settings = settings;
    }

    public abstract void paint(Canvas canvas, int x, int y);
}
