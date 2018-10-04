package ru.gameserver.model;

public class SingleCrashModel {

    private String macAddress;
    private String gameStart;
    private String crashLog;
    private String gameName;

    public SingleCrashModel(String macAddress, String gameStart, String crashLog, String gameName) {
        this.macAddress = macAddress;
        this.gameStart = gameStart;
        this.crashLog = crashLog;
        this.gameName = gameName;
    }

    public String getGameStart() {
        return gameStart;
    }

    public void setGameStart(String gameStart) {
        this.gameStart = gameStart;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getCrashLog() {
        return crashLog;
    }

    public void setCrashLog(String crashLog) {
        this.crashLog = crashLog;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
