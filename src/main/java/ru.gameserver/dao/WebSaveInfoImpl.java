package ru.gameserver.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WebSaveInfoImpl implements WebSaveInfo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WebSaveInfoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addContractor(String contractorName, String contractorPlace) {
        try {
            jdbcTemplate.execute("insert into game_info.contractor (name, place) values ('" + contractorName + "','" + contractorPlace + "')");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addGame(String gameName) {
        try {
            jdbcTemplate.execute("insert into game_info.games (game_name) values ('" + gameName + "')");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addContractorGames(int contractorId, int gameId) {
        try {
            jdbcTemplate.execute("insert into game_info.contractor_games (contractor_id, games_id) values (" + contractorId + "," + gameId + ")");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
