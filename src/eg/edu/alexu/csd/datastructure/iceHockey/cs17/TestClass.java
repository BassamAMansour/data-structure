package eg.edu.alexu.csd.datastructure.iceHockey.cs17;

import java.awt.*;

/**
 * Created by Bassam on 3/16/2017.
 */
public class TestClass {
    public static void main(String[] args) {
        PlaygroundImageAnalyser analyser = new PlaygroundImageAnalyser();
        Point[] pointsArray = analyser.findPlayers(
                new String[]{
                        "11111",
                        "1AAA1",
                        "1A1A1",
                        "1AAA1",
                        "11111"
                }, 1, 3);
        for (int i = 0; i < pointsArray.length; i++) {
            System.out.println(pointsArray[i]);
        }
    }
}
