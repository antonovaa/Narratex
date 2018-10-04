package ru.gameserver.model;

import java.io.Serializable;

public class GameInfoModel implements Serializable {
    private int Id;

    private String gameStart;

    private String playStart;

    private String gameEnd;

    private int playerCount;

    private String serverIp;

    private String place;

    public GameInfoModel(int id, String gameStart, int playerCount, String serverIp, String place) {
        Id = id;
        this.gameStart = gameStart;
        this.playerCount = playerCount;
        this.serverIp = serverIp;
        this.place = place;
    }

    public GameInfoModel(int id, String gameStart, String playStart, int playerCount, String serverIp, String place) {
        Id = id;
        this.gameStart = gameStart;
        this.playStart = playStart;
        this.playerCount = playerCount;
        this.serverIp = serverIp;
        this.place = place;
    }

    public GameInfoModel(int id, String gameStart, String playStart, String macAddr, int playerCount, String serverIp, String place) {
        Id = id;
        this.gameStart = gameStart;
        this.playStart = playStart;
        this.gameEnd = macAddr;
        this.playerCount = playerCount;
        this.serverIp = serverIp;
        this.place = place;
    }

    public String getGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(String gameEnd) {
        this.gameEnd = gameEnd;
    }

    public String getPlayStart() {
        return playStart;
    }

    public void setPlayStart(String playStart) {
        this.playStart = playStart;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getGameStart() {
        return gameStart;
    }

    public void setGameStart(String gameStart) {
        this.gameStart = gameStart;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
