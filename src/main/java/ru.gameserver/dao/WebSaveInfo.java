package ru.gameserver.dao;

public interface WebSaveInfo {

    public boolean addContractor(String contractorName,String contractorPlace);
    public boolean addGame(String gameName);
    public boolean addContractorGames(int contractorId,int gameId);

}
