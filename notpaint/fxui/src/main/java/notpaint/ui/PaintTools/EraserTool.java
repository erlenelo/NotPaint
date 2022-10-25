package notpaint.ui.painttools;

import javafx.scene.paint.Color;
import notpaint.ui.PaintSettings;

/**
 * Tool that erases the canvas, by replacing the color with white.
 */
public class EraserTool extends PenTool {

    public EraserTool(PaintSettings settings) {
        super(settings);
    }

    @Override
    protected Color getColor() {
        return Color.WHITE;
    }

}
