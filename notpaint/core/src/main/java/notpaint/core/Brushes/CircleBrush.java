package notpaint.core.brushes;

import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Stream;

/**
 * A brush that draws a circle around the point it is used on.
 */
public class CircleBrush extends SquareBrush {

    public CircleBrush(int size) {
        super(size);
    }

    @Override
    public Stream<SimpleEntry<Integer, Integer>> getPixels(int x, int y) {
        // Same as squarebrush, except filter out the pixels more
        // than size pixels away from (x, y)
        return super.getPixels(x, y)
                .filter(pair -> (y - pair.getValue()) * (y - pair.getValue())
                        + (x - pair.getKey()) * (x - pair.getKey()) < size * size);
    }
}
