package notpaint.core.Brushes;

import java.util.stream.Stream;

import javafx.scene.canvas.Canvas;
import javafx.util.Pair;

public abstract class Brush {

    public Brush(int size) {
        this.size = size;
    }

    public int size = 1;

    public abstract Stream<Pair<Integer, Integer>> getPixels(Canvas canvas, int x, int y);
}
