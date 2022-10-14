package notpaint.core.Brushes;

import java.util.AbstractMap.SimpleEntry;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SquareBrush extends Brush {

    public SquareBrush(int size) {
        super(size);
    }

    @Override
    public Stream<SimpleEntry<Integer, Integer>> getPixels(int x, int y) {
        // Return a stream of all pixels in a square around (x, y)
        return IntStream.range(y- size, y + size).boxed()
            .flatMap(y_coord -> IntStream.range(x - size, x + size)
            .mapToObj(x_coord -> new SimpleEntry<Integer, Integer>(x_coord, y_coord)));
    }
}
