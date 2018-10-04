package ru.gameserver.dao;

import java.util.List;
import java.util.Map;

public interface WebShowInfo {



    public List<Map<String, Object>> getContractors();
    public List<Map<String, Object>> getGames();
    public List<Map<String, Object>> showContractorGames(int contractorId);
    public List<Map<String, Object>> showInitGames(int contractorId);


    public List<Map<String, Object>> getGamesLog(String date_begin,String date_end,int contractor_id,int game_id);
    public List<Map<String, Object>> getGamesCrash(String date_begin,String date_end,int contractor_id,int game_id);


}
