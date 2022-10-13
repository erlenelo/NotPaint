package notpaint.core.Brushes;

import java.util.stream.Stream;

import javafx.scene.canvas.Canvas;
import javafx.util.Pair;

public class CircleBrush extends SquareBrush {
    
    public CircleBrush(int size) {
        super(size);
    }

    @Override
    public Stream<Pair<Integer, Integer>> getPixels(Canvas canvas, int x, int y) {
        // Same as squarebrush, except filter out the pixels more than size distance away from (x, y)
        return super.getPixels(canvas, x, y)
            .filter(pair -> (y-pair.getValue())*(y-pair.getValue()) + (x-pair.getKey())*(x-pair.getKey()) < size*size);        
    }
}