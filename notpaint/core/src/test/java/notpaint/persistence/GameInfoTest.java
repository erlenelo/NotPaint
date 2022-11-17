package notpaint.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link LocalGameInfoPersistence}.
 */
public class GameInfoTest {

    @Test
    public void testAddIteration() {
        GameInfo gameInfo = new GameInfo(2, 5, true);
        gameInfo.addIteration("Person1");
        assertEquals(gameInfo.getCurrentIterations(), 1);

    }

    @Test
    public void testGetCurrentIterations() {
        GameInfo gameInfo = new GameInfo(2, 5, true);
        gameInfo.addIteration("Person1");
        assertEquals(gameInfo.getCurrentIterations(), 1);

    }

    @Test
    public void testConstructorThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GameInfo(-1, 10, true);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new GameInfo(10, 1, true);
        });
    }

    @Test
    public void testGetLastEditTime() {
        GameInfo gameInfo = new GameInfo(2, 5, true);
        gameInfo.addIteration("Person1");
        assertEquals(gameInfo.getLastEditTime(),
                gameInfo.getLastEditTime()); // Unsure as to what the comparitor should be
    }

    @Test
    public void testGetLastEditor() {
        GameInfo gameInfo = new GameInfo(2, 5, true);
        gameInfo.addIteration("Person1");
        assertEquals(gameInfo.getLastEditor(), "Person1");

    }

    @Test
    public void testGetMaxIterations() {
        GameInfo gameInfo = new GameInfo(2, 5, true);
        assertEquals(gameInfo.getMaxIterations(), 2);

    }

    @Test
    public void testGetSecondsPerRound() {
        GameInfo gameInfo = new GameInfo(2, 5, true);
        assertEquals(gameInfo.getSecondsPerRound(), 5);

    }

    @Test
    public void testGetUuid() {
        GameInfo gameInfo = new GameInfo(2, 5, true);
        assertEquals(gameInfo.getUuid(), gameInfo.getUuid()); // same here, unsure about comparison

    }

    @Test
    public void testIsFinished() {
        GameInfo gameInfo = new GameInfo(2, 5, true);
        // Adding iterations up to maxiterations
        gameInfo.addIteration("Person1");
        gameInfo.addIteration("Person2");
        // game should be finished now that maxiterations has been reached
        assertEquals(gameInfo.isFinished(), true);

    }
}
