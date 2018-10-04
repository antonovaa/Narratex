package ru.gameserver.model;

public class Gkey {
    private String place;

    private String ip;

    private String key;

    private String mac;

    private String gameName;

    public Gkey() {
    }

    public Gkey(String place, String ip, String key, String mac) {
        this.place = place;
        this.ip = ip;
        this.key = key;
        this.mac = mac;
    }

    public Gkey(String place, String ip, String key, String mac, String gameName) {
        this.place = place;
        this.ip = ip;
        this.key = key;
        this.mac = mac;
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
