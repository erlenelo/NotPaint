package notpaint.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class GameInfo {

    private int maxIterations;      
    private int secondsPerRound;
    private boolean newWordEachRound;

    private int currentIterations;
    private List<String> words;
    private String lastEditor; // person who last edited
    
    private UUID uuid;

    private static Random random;

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
        
        uuid = UUID.randomUUID();
    }
    
    public void addIteration(String editor) { // last editor + counter 
        this.lastEditor = editor;
        
        increaseCurrentIterations();

        if(!isFinished()) {
            if(newWordEachRound)            
                generateNewWord();
        }
    }

    public String getImagePath() {
        // TODO: Determine location where this will be saved. For now will be saved relative to where program is running from
        return uuid.toString() + ".png";
    }

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
        // TODO: get a new random word from a text file (in resources maybe)
        
      
        var wordListString = new String(getClass().getClassLoader().getResourceAsStream("words.txt").readAllBytes());
        var wordListArray =  wordListString.split("\\r?\\n|\\r");

        if(random == null) random = new Random();
        int randomIndex = random.nextInt(wordListArray.length);

        // Add the word to the list of words
        words.add(wordListArray[randomIndex]);

    }

    public String getWord() {
        return words.get(words.size() - 1);
    }

    public String getLastEditor() {
        return lastEditor;
    }

    public int getSecondsPerRound() {
        return secondsPerRound;
    }

    public void saveSettings(String){
        List<> saved = new List<>(lastEditor, maxIterations, secondsPerRound,words,  
    }

}
