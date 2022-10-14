package notpaint.core;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


/**
 * One instance of the GameInfo class represents a Game (either ongoing or
 * finished)
 */
public class GameInfo {

    @JsonSerialize @JsonDeserialize
    private int maxIterations;
    @JsonSerialize @JsonDeserialize
    private int secondsPerRound;
    @JsonSerialize @JsonDeserialize
    private boolean newWordEachRound;

    @JsonSerialize @JsonDeserialize
    private int currentIterations;
    @JsonSerialize @JsonDeserialize
    private List<String> words;
    @JsonSerialize @JsonDeserialize
    private String lastEditor; // person who last edited
    @JsonSerialize @JsonDeserialize
    private Date lastEditTime; 

    @JsonSerialize @JsonDeserialize
    private UUID uuid;

    private static final Random random = new Random();

    /**
     * 
     * @param maxIterations    The number of iterations before this game is finished
     * @param secondsPerRound  How many seconds the users should have to draw each
     *                         iteration
     * @param newWordEachRound Whether there should be generated a new word each
     *                         round. If false there will not be a new word after an
     *                         iteration.
     */
    public GameInfo(int maxIterations, int secondsPerRound, boolean newWordEachRound) {
        if (maxIterations < 1)
            throw new IllegalArgumentException("Argument maxIterations must be greater than 0");

        if (secondsPerRound < 5 || secondsPerRound > 120)
            throw new IllegalArgumentException("Argument secondsPerRound must be on interval [5, 120]");

        this.words = new ArrayList<String>();
        this.maxIterations = maxIterations;
        this.secondsPerRound = secondsPerRound;
        this.newWordEachRound = newWordEachRound;
        this.lastEditor = "N/A";
        this.lastEditTime = new Date();

        generateNewWord();

        uuid = UUID.randomUUID();
    }
    protected GameInfo() {}

    /**
     * Add a new iteration to the Game. Generates a new word if newWordEachRound was
     * set to true
     * 
     * @param editor The nickname of the person who edited the image for this
     *               iteration
     */
    public void addIteration(String editor) { // last editor + counter
        this.lastEditor = editor;
        this.lastEditTime = new Date();
        increaseCurrentIterations();

        if (!isFinished()) {
            if (newWordEachRound)
                generateNewWord();
        }
    }



    /**
     * Chech if the game is finished (currentIterations has reached maxIterations)
     * 
     * @return true if finished, false if not
     */
    public boolean isFinished() {
        return currentIterations == maxIterations;
    }

    private void increaseCurrentIterations() {
        if (currentIterations + 1 > maxIterations)
            throw new UnsupportedOperationException("Cannot increase currentIterations over maxIterations");
        currentIterations++;
    }

    public int getCurrentIterations() {
        return currentIterations;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Date getLastEditTime() {
        return new Date(lastEditTime.getTime());
    }

    private void generateNewWord() {
        // get a new random word from a text file (in resources)
        String wordListString = null;
        try(var inputStream = getClass().getResourceAsStream("words.txt")) {
            wordListString = new String(inputStream.readAllBytes(), Charset.forName("UTF-8"));
    
            var wordListArray = wordListString.split("\\r?\\n|\\r");
            int randomIndex = random.nextInt(wordListArray.length);
            // Add the word to the list of words
            words.add(wordListArray[randomIndex]);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Returns the word that is currently to be drawn.
     * 
     * @return
     */
    public String getWord() {
        return words.get(words.size() - 1);
    }

    public String getLastEditor() {
        return lastEditor;
    }

    public int getSecondsPerRound() {
        return secondsPerRound;
    }

    @Override
    public String toString() {
        return "GameInfo: maxIterations: " + maxIterations + ", secondsperround: " + secondsPerRound
                + "newWordEachRound: " + newWordEachRound;
    }

}
