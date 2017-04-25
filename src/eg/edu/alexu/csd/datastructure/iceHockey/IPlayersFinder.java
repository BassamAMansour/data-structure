package eg.edu.alexu.csd.datastructure.iceHockey;

/**
 * Created by Bassam on 3/15/2017.
 */
public interface IPlayersFinder {
    /**
     * Search for players locations at the given photo
     *
     * @param photo     Two dimension array of photo contents
     *                  Will contain between 1 and 50 elements, inclusive
     * @param team      Identifier of the team
     * @param threshold Minimum area for an element
     *                  Will be between 1 and 10000, inclusive
     * @return Array of players locations of the given team
     */
    java.awt.Point[] findPlayers(String[] photo, int team, int threshold);
}
