package com.company;

public class Player {
    private String playerName;
    private int points;
    private boolean currentTurn;
    private String color;

    /** *
     * Set a player information
     * @param playerName name of player
     * @param color color that the player should put
     * @param points points of the player
     * @param turn check if current turn
     */
    public Player(String playerName, String color, int points, boolean turn) {
        this.playerName = playerName;
        this.color = color;
        this.points = points;
        this.currentTurn = turn;
    }

    /**
     * Get the color of the player
     * @return the color of the player
     */
    public String getPlayerColor() {
        return color;
    }

    /**
     * Get the name of the player
     * @return the name of the player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Set points
     * @param points set points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Get the points
     * @return the points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Set the player's turn
     * @param currentTurn true if it is the player's turn
     */
    public void setTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * Get the player's turn
     * @return the player's turn
     */
    public boolean getTurn() {
        return this.currentTurn;
    }

}
