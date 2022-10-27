package notpaint.ui.painttools;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import notpaint.ui.PaintSettings;

/**
 * Tool that draws on the canvas, using the current brush and color.
 */
public class PenTool extends Tool {

    public PenTool(PaintSettings settings) {
        super(settings);
    }

    @Override
    public void paint(Canvas canvas, int x, int y) {
        var pixels = settings.getBrush().getPixels(x, y);
        var pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        pixels.forEach(pair -> {
            pixelWriter.setColor(pair.getKey(), pair.getValue(), getColor());
        });

    }

    protected Color getColor() {
        return settings.getColor();
    }

}
