package notpaint.core.PaintTools;

import notpaint.core.PaintSettings;
import javafx.scene.paint.Color;

public class EraserTool extends PenTool{

    public EraserTool(PaintSettings settings) {
        super(settings);
    }

    @Override
    protected Color getColor() {
        return Color.WHITE;
    }
    
}
