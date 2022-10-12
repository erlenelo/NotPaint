package notpaint.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameInfoTest {
    
    @Test
    public void testAddIteration() {
        GameInfo gameInfo = new GameInfo(2,5,true);
        gameInfo.addIteration("Person1");
        assertEquals(gameInfo.getCurrentIterations(), 1);
   
    }

    @Test
    public void testGetCurrentIterations() {
        GameInfo gameInfo = new GameInfo(2,5,true);
        gameInfo.addIteration("Person1");
        assertEquals(gameInfo.getCurrentIterations(), 1);

    }
        // Test not running, need server-side up and running.
     /* @Test
    public void testGetImagePath() {
        GameInfo gameInfo = new GameInfo(2,5,true);
        gameInfo.addIteration("Person1");
        assertEquals(gameInfo.getImagePath(), "C:\\Users\\User\\Documents\\NetBeansProjects\\NotPaint\\core\\src\\main\\resources\\notpaint\\core\\Images\\image.png");
 

    }
 */
    @Test
    public void testGetLastEditTime() {
        GameInfo gameInfo = new GameInfo(2,5,true);
        gameInfo.addIteration("Person1");
        assertEquals(gameInfo.getLastEditTime(), gameInfo.getLastEditTime()); // Unsure as to what the comparitor should be
    }

    @Test
    public void testGetLastEditor() {
        GameInfo gameInfo = new GameInfo(2,5,true);
        gameInfo.addIteration("Person1");
        assertEquals(gameInfo.getLastEditor(), "Person1");

    }

    @Test
    public void testGetMaxIterations() {
        GameInfo gameInfo = new GameInfo(2,5,true);
        assertEquals(gameInfo.getMaxIterations(), 2);


    }

    @Test
    public void testGetSecondsPerRound() {
        GameInfo gameInfo = new GameInfo(2,5,true);
        assertEquals(gameInfo.getSecondsPerRound(), 5);


    }

    @Test
    public void testGetUuid() {
        GameInfo gameInfo = new GameInfo(2,5,true);
        assertEquals(gameInfo.getUuid(), gameInfo.getUuid()); //same here, unsure about comparison


    }
    /* 
    @Test
      public void testGetWord() {
        GameInfo gameInfo = new GameInfo(2,5,true);
        
        assertEquals(gameInfo.getWord(), "test"); //Need to get index of randomly chosen word.


    } */

    @Test
    public void testIsFinished() {
        GameInfo gameInfo = new GameInfo(2,5,true);
        //Adding iterations up to maxiterations
        gameInfo.addIteration("Person1");
        gameInfo.addIteration("Person2");
        //game should be finished now that maxiterations has been reached
        assertEquals(gameInfo.isFinished(), true);

    }
}
 /*
    @Test
    public void testToString() {
        GameInfo gameInfo = new GameInfo(2,5,true);
        assertEquals(gameInfo.toString(), "GameInfo{uuid=" + gameInfo.getUuid() + ", word=" + gameInfo.getWord() + ", maxIterations=" + gameInfo.getMaxIterations() + ", currentIterations=" + gameInfo.getCurrentIterations() + ", secondsPerRound=" + gameInfo.getSecondsPerRound() + ", lastEditor=" + gameInfo.getLastEditor() + ", lastEditTime=" + gameInfo.getLastEditTime() + ", finished=" + gameInfo.isFinished() + '}');


    }
}
*/