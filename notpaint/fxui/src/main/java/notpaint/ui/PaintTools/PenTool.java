package notpaint.ui.PaintTools;

import notpaint.core.PaintSettings;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class PenTool extends Tool {

    public PenTool(PaintSettings settings) {
        super(settings);
    }

    @Override
    public void Paint(Canvas canvas, int x, int y) {
        var pixels = settings.getBrush().getPixels(canvas, x, y);
        var pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        pixels.forEach(pair -> {
            pixelWriter.setColor(pair.getKey(), pair.getValue(), getColor());
        });

    }

    protected Color getColor() {
        return settings.getColor();
    }

}
