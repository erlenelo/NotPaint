package gr2213.PaintTools;

import gr2213.PaintSettings;
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
