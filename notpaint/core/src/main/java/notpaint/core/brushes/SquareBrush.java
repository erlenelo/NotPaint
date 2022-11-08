package notpaint.core.brushes;

import java.util.AbstractMap.SimpleEntry;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A brush that draws a square around the point it is used on.
 */
public class SquareBrush extends Brush {

    public SquareBrush(int size) {
        super(size);
    }

    @Override
    public Stream<SimpleEntry<Integer, Integer>> getPixels(int x, int y) {
        // Return a stream of all pixels in a square around (x, y)
        return IntStream.range(y - size, y + size).boxed()
                .flatMap(newY -> IntStream.range(x - size, x + size)
                        .mapToObj(newX -> new SimpleEntry<Integer, Integer>(newX, newY)));
    }
}
