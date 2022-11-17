package notpaint.ui.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link LineUtilTest}.
 */
public class LineUtilTest {

    @Test
    public void testGetLine1() {

        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        list.add(new Pair<Integer, Integer>(0, 0));
        list.add(new Pair<Integer, Integer>(1, 0));
        list.add(new Pair<Integer, Integer>(2, 0));
        list.add(new Pair<Integer, Integer>(3, 0));
        list.add(new Pair<Integer, Integer>(4, 0));
        list.add(new Pair<Integer, Integer>(5, 0));
        list.add(new Pair<Integer, Integer>(6, 0));
        list.add(new Pair<Integer, Integer>(7, 0));
        list.add(new Pair<Integer, Integer>(8, 0));
        list.add(new Pair<Integer, Integer>(9, 0));
        list.add(new Pair<Integer, Integer>(10, 0));

        List<Pair<Integer, Integer>> list1 = LineUtil.getAllPixelsBetween(0, 0, 10, 0);

        assertTrue(list1.equals(list));
    }

    @Test
    public void testGetLine2() {
        List<Pair<Integer, Integer>> list1 = LineUtil.getAllPixelsBetween(0, 10, 0, 0);
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        list.add(new Pair<Integer, Integer>(0, 10));
        list.add(new Pair<Integer, Integer>(0, 9));
        list.add(new Pair<Integer, Integer>(0, 8));
        list.add(new Pair<Integer, Integer>(0, 7));
        list.add(new Pair<Integer, Integer>(0, 6));
        list.add(new Pair<Integer, Integer>(0, 5));
        list.add(new Pair<Integer, Integer>(0, 4));
        list.add(new Pair<Integer, Integer>(0, 3));
        list.add(new Pair<Integer, Integer>(0, 2));
        list.add(new Pair<Integer, Integer>(0, 1));
        list.add(new Pair<Integer, Integer>(0, 0));

        boolean containsAll = true;
        for (Pair<Integer, Integer> pair : list) {
            if (!list1.contains(pair)) {
                containsAll = false;
            }

        }
        assertTrue(containsAll);

    }
}
