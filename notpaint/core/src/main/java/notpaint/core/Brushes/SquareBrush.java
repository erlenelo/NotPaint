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
    public Stream<Pair<Integer, Integer>> GetPixels(Canvas canvas, int x, int y) {
        // Return a stream of all pixels in a square around (x, y)
        return IntStream.range(y- Size, y + Size).boxed()
            .flatMap(y_coord -> IntStream.range(x - Size, x + Size)
            .mapToObj(x_coord -> new Pair<Integer, Integer>(x_coord, y_coord)));
    }
}
