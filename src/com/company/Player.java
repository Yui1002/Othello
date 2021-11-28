package com.company;

public class Player {
    private String playerName;
    private int points;
    private boolean currentTurn;
    private String color;

    public Player(String playerName, String color, int points, boolean turn) {
        this.playerName = playerName;
        this.color = color;
        this.points = points;
        this.currentTurn = turn;
    }

    public String getPlayerColor() {
        return color;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    public void setTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
    }

    public boolean getTurn() {
        return this.currentTurn;
    }

}
