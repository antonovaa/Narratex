package ru.gameserver.dao;

public interface WebDelete {

    public boolean  deleteContractor(int contractor_id);
    public boolean  deleteGames(int games_id);
    public boolean  deleteContractorGames(int contractor_games_id);


}
