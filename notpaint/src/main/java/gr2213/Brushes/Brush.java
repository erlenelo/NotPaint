package gr2213.Brushes;

import java.util.stream.Stream;

import javafx.scene.canvas.Canvas;
import javafx.util.Pair;

public abstract class Brush {

    public Brush(int Size) {
        this.Size = Size;
    }

    public int Size = 1;
    public abstract Stream<Pair<Integer, Integer>> GetPixels(Canvas canvas, int x, int y);
}
