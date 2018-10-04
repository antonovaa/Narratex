package ru.gameserver.dao;

import ru.gameserver.model.Gkey;

import java.util.List;
import java.util.Map;

public interface ContractorGame {

    public String changeResolution(int init_contractor_game_mac_key_id);

    public Map<String,Object> getMd5Key(int contractor_games_id);

    public String getExistMd5Key(String macAddress,String gameName);

    public String generateAndSaveMd5Key(int contractor_games_id,int count,boolean isConstantine);
    public List<Map<String, Object>> getAllMd5Keys();


    public String InitSaveGkey(Gkey gkey);

    public String getKeyToday(int contractor_games_id);
    public String getHash(String str);

    public boolean decreaseCountKey(int id);

}
