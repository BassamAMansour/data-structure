package eg.edu.alexu.csd.datastructure.iceHockey.cs17;

import eg.edu.alexu.csd.datastructure.iceHockey.IPlayersFinder;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Bassam on 3/15/2017.
 */
public class PlaygroundImageAnalyser implements IPlayersFinder {

    private boolean dummyDFSArray[][];
    private ArrayList<PlayerRegion> playersPlaces = new ArrayList<>();
    private String[] photo;
    private int team;
    private String teamString;
    private int threshold;
    private static final int NUMBER_OF_PIXELS_PER_SQUARE = 4;


    @Override
    public Point[] findPlayers(String[] photo, int team, int threshold) {

        this.photo = photo;
        this.team = team;
        this.teamString = Integer.toString(team);
        this.threshold = threshold;

        initializeDummyDFSArray();
        initializePlayersPlacesArrayList();
        Point[] playerCenters = getPlayersCenters(playersPlaces);
        playerCenters = sortPlayersPositionsByX(playerCenters);

        return playerCenters;
    }

    private Point[] sortPlayersPositionsByX(Point[] playerCenters) {
        Arrays.sort(playerCenters, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare((int) o1.getX(), (int) o2.getX());
            }
        });
        return playerCenters;
    }


    private void initializeDummyDFSArray() {
        boolean[][] dummy;
        dummy = new boolean[photo.length][photo[0].length()];
        this.dummyDFSArray = dummy;
    }

    private void initializePlayersPlacesArrayList() {
        searchForPlayerInPhoto();
    }

    private void searchForPlayerInPhoto() {

        for (int row = 0; row < photo.length; row++) {
            for (int column = 0; column < photo[0].length(); column++) {

                String imagePixelString = getImagePixelString(row, column);

                if (imagePixelString.equalsIgnoreCase(teamString) && !dummyDFSArray[row][column]) {

                    PlayerRegion player = new PlayerRegion();

                    player.upperSquareRow = row;
                    player.upperSquareColumn = column;
                    player.lowerSquareRow = row;
                    player.lowerSquareColumn = column;
                    player.numberOfSquares = 1;

                    player = searchPlayerAdjacentSquaresDFS(row, column, player);
                    addPlayerToPlayersPlacesIfAboveThreshold(player);
                }
            }
        }

    }


    private PlayerRegion searchPlayerAdjacentSquaresDFS(int row, int column, PlayerRegion player) {

        player = adjustPlayerBoundaries(player, row, column);
        dummyDFSArray[row][column] = true;

        if (!isOutOfPhotoRange(row + 1, column))
            if (!dummyDFSArray[row + 1][column])
                if (getImagePixelString(row + 1, column).equalsIgnoreCase(teamString)) {
                    player.numberOfSquares++;
                    player = searchPlayerAdjacentSquaresDFS(row + 1, column, player);
                }
        if (!isOutOfPhotoRange(row - 1, column))
            if (!dummyDFSArray[row - 1][column])
                if (getImagePixelString(row - 1, column).equalsIgnoreCase(teamString)) {
                    player.numberOfSquares++;
                    player = searchPlayerAdjacentSquaresDFS(row - 1, column, player);
                }
        if (!isOutOfPhotoRange(row, column + 1))
            if (!dummyDFSArray[row][column + 1])
                if (getImagePixelString(row, column + 1).equalsIgnoreCase(teamString)) {
                    player.numberOfSquares++;
                    player = searchPlayerAdjacentSquaresDFS(row, column + 1, player);
                }
        if (!isOutOfPhotoRange(row, column - 1))
            if (!dummyDFSArray[row][column - 1])
                if (getImagePixelString(row, column - 1).equalsIgnoreCase(teamString)) {
                    player.numberOfSquares++;
                    player = searchPlayerAdjacentSquaresDFS(row, column - 1, player);
                }

        return player;
    }

    private PlayerRegion adjustPlayerBoundaries(PlayerRegion player, int row, int column) {

        if (player.upperSquareRow > row) {
            player.upperSquareRow = row;
        }
        if (player.upperSquareColumn > column) {
            player.upperSquareColumn = column;
        }
        if (player.lowerSquareRow < row) {
            player.lowerSquareRow = row;
        }
        if (player.lowerSquareColumn < column) {
            player.lowerSquareColumn = column;
        }
        return player;
    }

    private void addPlayerToPlayersPlacesIfAboveThreshold(PlayerRegion player) {
        if (player.numberOfSquares * NUMBER_OF_PIXELS_PER_SQUARE >= threshold) {
            this.playersPlaces.add(player);
        }
    }

    private String getImagePixelString(int row, int column) {
        return Character.toString(photo[row].charAt(column));
    }

    private boolean isOutOfPhotoRange(int row, int column) {

        if (row < 0 || row > photo.length - 1 || column < 0 || column > photo[0].length() - 1) {
            return true;
        } else {
            return false;
        }
    }

    private Point[] getPlayersCenters(ArrayList<PlayerRegion> playersPlaces) {

        Point[] playersCenters = new Point[playersPlaces.size()];

        for (int index = 0; index < playersCenters.length; index++) {
            playersCenters[index] = getCenterPoint(playersPlaces.get(index));
        }
        return playersCenters;
    }

    private Point getCenterPoint(PlayerRegion player) {

        int centerRow;
        int centerColumn;

        centerRow = player.upperSquareRow + player.lowerSquareRow + 1;
        centerColumn = player.upperSquareColumn + player.lowerSquareColumn + 1;
        return new Point(centerColumn, centerRow);
    }

}
