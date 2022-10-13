package notpaint.ui.PaintTools;

import javafx.scene.paint.Color;
import notpaint.ui.PaintSettings;

public class EraserTool extends PenTool {

    public EraserTool(PaintSettings settings) {
        super(settings);
    }

    @Override
    protected Color getColor() {
        return Color.WHITE;
    }

}
