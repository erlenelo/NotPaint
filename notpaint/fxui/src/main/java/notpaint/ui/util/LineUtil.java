package notpaint.ui.util;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;


/**
 * Utility class for line computations.
 */
public class LineUtil {
    
    /**
     * Calculates all points between two points, using Breseham's line algorithm.
     *
     * @param x0 The x coordinate of the first point.
     * @param y0 The y coordinate of the first point.
     * @param x1 The x coordinate of the second point.
     * @param y1 The y coordinate of the second point.
     * 
     * @return List of all points on the line between the two points.
     */
    public static List<Pair<Integer, Integer>> getAllPixelsBetween(int x0, int y0, int x1, int y1) {
        if (Math.abs(y1 - y0) < Math.abs(x1 - x0)) {
            if (x0 > x1) {
                return plotLineLow(x1, y1, x0, y0);
            } else {
                return plotLineLow(x0, y0, x1, y1);
            }
        } else {
            if (y0 > y1) {
                return plotLineHigh(x1, y1, x0, y0);
            } else {
                return plotLineHigh(x0, y0, x1, y1);
            }
        }
    }

    private static List<Pair<Integer, Integer>> plotLineLow(int x0, int y0, int x1, int y1) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        int yi = 1;
        if (dy < 0) {
            yi = -1;
            dy = -dy;
        }
        int d = 2 * dy - dx;
        int y = y0;

        List<Pair<Integer, Integer>> pixels = new ArrayList<>();
        for (int x = x0; x <= x1; x++) {
            pixels.add(new Pair<Integer, Integer>(x, y));
            if (d > 0) {
                y = y + yi;
                d = d + (2 * (dy - dx));
            } else {
                d = d + 2 * dy;
            }
        }
        return pixels;
    }

    private static List<Pair<Integer, Integer>> plotLineHigh(int x0, int y0, int x1, int y1) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        int xi = 1;
        if (dx < 0) {
            xi = -1;
            dx = -dx;
        }
        int d = 2 * dx - dy;
        int x = x0;

        List<Pair<Integer, Integer>> pixels = new ArrayList<>();
        for (int y = y0; y <= y1; y++) {
            pixels.add(new Pair<Integer, Integer>(x, y));
            if (d > 0) {
                x = x + xi;
                d = d + (2 * (dx - dy));
            } else {
                d = d + 2 * dx;
            }
        }
        return pixels;
    }

}
