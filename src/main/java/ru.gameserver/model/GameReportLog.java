package ru.gameserver.model;

import java.util.List;

public class GameReportLog {

    private String macAddress;
    private String gameLocation;
    private List<String> log;
    private String gameName;

    public String getGameName() {
        return gameName;
    }

    public GameReportLog(String macAddress, String gameLocation, String log, String gameName) {
        this.macAddress = macAddress;
        this.gameLocation = gameLocation;
        this.gameName = gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public GameReportLog(String asd) {
        this.macAddress = asd;
    }


    public GameReportLog(String email, String password) {
        this.macAddress = email;
        this.gameLocation = password;
    }

    public GameReportLog(String macAddress, String gameLocation, List<String> log, String gameName) {
        this.macAddress = macAddress;
        this.gameLocation = gameLocation;
        this.log = log;
        this.gameName = gameName;
    }

    public List<String> getLog() {
        return log;
    }

    public void setLog(List<String> log) {
        this.log = log;
    }

    public String getGameLocation() {
        return gameLocation;
    }

    public void setGameLocation(String gameLocation) {
        this.gameLocation = gameLocation;
    }
}
