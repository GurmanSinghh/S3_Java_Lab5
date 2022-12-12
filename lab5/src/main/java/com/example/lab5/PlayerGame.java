package com.example.lab5;

import java.time.LocalDate;

public class PlayerGame {
    private int playerGameId;
    private int gameId;
    private int playerId;
    private LocalDate playingDate;
    private int score;
    public PlayerGame(int gameId, int playerId, LocalDate playingDate, int score) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.playingDate = playingDate;
        this.score = score;
    }
    public PlayerGame() {
    }
    public int getPlayerGameId() {
        return playerGameId;
    }
    public void setPlayerGameId(int playerGameId) {
        this.playerGameId = playerGameId;
    }
    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public LocalDate getPlayingDate() {
        return playingDate;
    }
    public void setPlayingDate(LocalDate playingDate) {
        this.playingDate = playingDate;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }


}
