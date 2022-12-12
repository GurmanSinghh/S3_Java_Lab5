package com.example.lab5;

public class Game {
    private int gameId;
    private String gameTitle;
    public Game(String gameTitle) {
        this.gameTitle = gameTitle;
    }
    public Game() {
    }
    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    public String getGameTitle() {
        return gameTitle;
    }
    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

}
