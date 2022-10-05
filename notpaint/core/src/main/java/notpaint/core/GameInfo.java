package notpaint.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * One instance of the GameInfo class represents a Game (either ongoing or finished)
 */
public class GameInfo {

    private int maxIterations;      
    private int secondsPerRound;
    private boolean newWordEachRound;

    private int currentIterations;
    private List<String> words;
    private String lastEditor; // person who last edited
    
    private UUID uuid;

    private static Random random;

    /**
     * 
     * @param maxIterations The number of iterations before this game is finished
     * @param secondsPerRound How many seconds the users should have to draw each iteration
     * @param newWordEachRound Whether there should be generated a new word each round. If false there will not be a new word after an iteration.
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

        generateNewWord();
        
        uuid = UUID.randomUUID();
    }
    
    /**
     * Add a new iteration to the Game. Generates a new word if newWordEachRound was set to true
     * @param editor The nickname of the person who edited the image for this iteration
     */
    public void addIteration(String editor) { // last editor + counter 
        this.lastEditor = editor;
        
        increaseCurrentIterations();

        if(!isFinished()) {
            if(newWordEachRound)            
                generateNewWord();
        }
    }

    /**
     * 
     * @return String representing the path to image. Could be a file or web address.
     */
    public String getImagePath() {
        // TODO: Determine location where this will be saved. For now will be saved relative to where program is running from
        return uuid.toString() + ".png";
    }

    /**
     * Chech if the game is finished (currentIterations has reached maxIterations)
     * @return true if finished, false if not
     */
    public boolean isFinished() {
        return currentIterations == maxIterations;
    }

    private void increaseCurrentIterations() {
        if(currentIterations + 1 > maxIterations)
            throw new UnsupportedOperationException("Cannot increase currentIterations over maxIterations");
        currentIterations++;
    }

    public int getCurrentIterations() {
        return currentIterations;
    }

    
    private void generateNewWord() {
        // get a new random word from a text file (in resources)
        String wordListString = null;
        try{
            wordListString = new String(getClass().getClassLoader().getResourceAsStream("words.txt").readAllBytes());
        } catch(IOException exception) {
            exception.printStackTrace();
        }
        var wordListArray =  wordListString.split("\\r?\\n|\\r");

        if(random == null) random = new Random();
        int randomIndex = random.nextInt(wordListArray.length);

        // Add the word to the list of words
        words.add(wordListArray[randomIndex]);

    }

    /**
     * Returns the word that is currently to be drawn.
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


}
