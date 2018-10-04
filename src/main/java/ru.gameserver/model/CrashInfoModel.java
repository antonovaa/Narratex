package ru.gameserver.model;



public class CrashInfoModel {

    private int Id;
    private String playerIp;
    private String serverIp;
    private String logPlayer;
    private String logServer;
    private String gameStart ;

    public CrashInfoModel(int id, String playerIp, String serverIp, String logPlayer, String logServer, String gameStart) {
        Id = id;
        this.playerIp = playerIp;
        this.serverIp = serverIp;
        this.logPlayer = logPlayer;
        this.logServer = logServer;
        this.gameStart = gameStart;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPlayerIp() {
        return playerIp;
    }

    public void setPlayerIp(String playerIp) {
        this.playerIp = playerIp;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getLogPlayer() {
        return logPlayer;
    }

    public void setLogPlayer(String logPlayer) {
        this.logPlayer = logPlayer;
    }

    public String getLogServer() {
        return logServer;
    }

    public void setLogServer(String logServer) {
        this.logServer = logServer;
    }

    public String getGameStart() {
        return gameStart;
    }

    public void setGameStart(String gameStart) {
        this.gameStart = gameStart;
    }
}
