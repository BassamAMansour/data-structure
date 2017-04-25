package eg.edu.alexu.csd.datastructure.hangman.cs17;

import eg.edu.alexu.csd.datastructure.hangman.IHangman;
import org.junit.Assert;
import org.junit.Test;
public class GameEngineTest {
    @Test
    public void testRandomWord() {
        IHangman hangman = new GameEngine();
        String secret = hangman.selectRandomSecretWord();
        Assert.assertNull("Random word returned", secret);
        String dictionary[] = new String[] { "XXX", "YYYY" };
        hangman.setDictionary(dictionary);
        secret = hangman.selectRandomSecretWord();
        Assert.assertNotNull("Null random word", secret);
        boolean found = false;
        for(int i=0; i<dictionary.length; i++)
            if(dictionary[i].equals(secret)){
                found = true;
            }
        Assert.assertTrue("Message not found", found);
    }

    @Test
    public void testWrongGuess() {
        IHangman hangman = new GameEngine();
        String dictionary[] = new String[] { "EGYPT" };
        hangman.setDictionary(dictionary);
        hangman.selectRandomSecretWord();
        hangman.setMaxWrongGuesses(2);
        Assert.assertEquals("-----", hangman.guess('X'));
    }

    @Test
    public void testCorrectGuess() {
        IHangman hangman = new GameEngine();
        String dictionary[] = new String[] { "EGYPT" };
        hangman.setDictionary(dictionary);
        hangman.selectRandomSecretWord();
        hangman.setMaxWrongGuesses(2);
        Assert.assertEquals("--Y--", hangman.guess('Y'));
    }

    @Test
    public void testCorrectGuessFirstChar() {
        IHangman hangman = new GameEngine();
        String dictionary[] = new String[] { "EGYPT" };
        hangman.setDictionary(dictionary);
        hangman.selectRandomSecretWord();
        hangman.setMaxWrongGuesses(2);
        Assert.assertEquals("E----", hangman.guess('E'));
    }

    @Test
    public void testCorrectGuessLastChar() {
        IHangman hangman = new GameEngine();
        String dictionary[] = new String[] { "EGYPT" };
        hangman.setDictionary(dictionary);
        hangman.selectRandomSecretWord();
        hangman.setMaxWrongGuesses(2);
        Assert.assertEquals("----T", hangman.guess('T'));
    }

    @Test
    public void testCaseSensitiveLower() {
        IHangman hangman = new GameEngine();
        String dictionary[] = new String[] { "EGYPT" };
        hangman.setDictionary(dictionary);
        hangman.selectRandomSecretWord();
        hangman.setMaxWrongGuesses(2);
        Assert.assertTrue("E----".equalsIgnoreCase(hangman.guess('e')));
    }

    @Test
    public void testCaseSensitiveUpper() {
        IHangman hangman = new GameEngine();
        String dictionary[] = new String[] { "egypt" };
        hangman.setDictionary(dictionary);
        hangman.selectRandomSecretWord();
        hangman.setMaxWrongGuesses(2);
        Assert.assertTrue("E----".equalsIgnoreCase(hangman.guess('E')));
    }

    @Test
    public void testFailCount() {
        IHangman hangman = new GameEngine();
        String dictionary[] = new String[] { "EGYPT" };
        hangman.setDictionary(dictionary);
        hangman.selectRandomSecretWord();
        int max = 5;
        hangman.setMaxWrongGuesses(max);
        for(int i=0; i<max; i++){
            String result = hangman.guess('X');
            if(i<max-1)
                Assert.assertEquals("Invalid Showing of Characters", "-----", result);
            else
                Assert.assertNull("Game must end!", result);
        }
    }

}