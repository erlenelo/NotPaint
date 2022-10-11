package notpaint.core.Brushes;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import javafx.scene.canvas.Canvas;
import javafx.util.Pair;

public class SquareBrush extends Brush {

    public SquareBrush(int size) {
        super(size);
    }

    @Override
    public Stream<Pair<Integer, Integer>> getPixels(Canvas canvas, int x, int y) {
        // Return a stream of all pixels in a square around (x, y)
        return IntStream.range(y- size, y + size).boxed()
            .flatMap(y_coord -> IntStream.range(x - size, x + size)
            .mapToObj(x_coord -> new Pair<Integer, Integer>(x_coord, y_coord)));
    }
}
