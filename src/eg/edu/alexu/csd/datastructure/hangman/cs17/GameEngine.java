package eg.edu.alexu.csd.datastructure.hangman.cs17;

import eg.edu.alexu.csd.datastructure.hangman.IHangman;

import java.util.Random;

public class GameEngine implements IHangman {

    private String[] dictionaryArray;
    private String secretWord;
    private String dashedWord;
    private int maxWrongGuesses;
    private int wrongGuesses;


    @Override
    public void setDictionary(String[] words) {
        this.dictionaryArray = words;
    }

    @Override
    public String selectRandomSecretWord() {
    	if (dictionaryArray == null)return null;
        int randomInt = getRandomNumber(dictionaryArray.length);
        this.secretWord = dictionaryArray[randomInt];
        this.dashedWord = generateDashedWord(secretWord);
        return secretWord;
    }

    @Override
    public String guess(Character c) {
        String localDashedWord = this.dashedWord.toUpperCase();
        String localSecretWord = this.secretWord.toUpperCase();
        String guessedLetter = c.toString().toUpperCase();
        if (c != null) {
            if (!localDashedWord.contains(guessedLetter)) {
                if (!localSecretWord.contains(guessedLetter)) {
                    wrongGuesses++;
                    if (isLooser()) {
                        return null;
                    }
                } else {
                    revealLetterInDashedWord(c);
                }
            }
        }
        return dashedWord;
    }

    private void revealLetterInDashedWord(Character c) {
        String localSecretWord = this.secretWord.toUpperCase();
        StringBuilder localDashedWord = new StringBuilder(this.dashedWord.toUpperCase());
        for (int i = 0; i < localDashedWord.length(); i++) {
            if (localSecretWord.charAt(i) == Character.toUpperCase(c)) {
                localDashedWord.setCharAt(i, secretWord.charAt(i));
            }
        }
        this.dashedWord = localDashedWord.toString();
    }

    @Override
    public void setMaxWrongGuesses(Integer max) {
        if ((max >= 0) && (max != null)) {
            this.maxWrongGuesses = max;
        } else {
            this.maxWrongGuesses = 0;
        }
    }

    private int getRandomNumber(int max) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(max);
    }

    private String generateDashedWord(String secretWord) {
        String dashedWord = "";
        for (int i = 0; i < secretWord.length(); i++) {
            dashedWord += "-";
        }
        return dashedWord;
    }

    public boolean isWinner() {
        return !dashedWord.contains("-");
    }

    public boolean isLooser() {
        return wrongGuesses >= maxWrongGuesses;
    }
}

