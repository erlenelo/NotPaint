package notpaint.core.brushes;

import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Stream;

/**
 * Abstract class for brushes.
 * Implementations should override getPixels to return a
 * stream of pixels that the brush should draw on.
 */
public abstract class Brush {

    public Brush(int size) {
        this.size = size;
    }

    public int size = 1;

    /**
     * Returns a stream of all the pixel coordinates that this brush should apply to
     * when used on (x, y).
     */
    public abstract Stream<SimpleEntry<Integer, Integer>> getPixels(int x, int y);
}
