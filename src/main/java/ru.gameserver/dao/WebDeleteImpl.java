package ru.gameserver.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class WebDeleteImpl implements WebDelete {



    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WebDeleteImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean deleteContractor(int contractor_id) {
        try {
            String sql = "select game_info.delete_contractor("+contractor_id+")";
            jdbcTemplate.execute(sql);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }

    @Override
    public boolean deleteGames(int games_id) {
        try {
            String sql = "select game_info.delete_games("+games_id+")";
            jdbcTemplate.execute(sql);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }

    @Override
    public boolean deleteContractorGames(int contractor_games_id) {
        try {
            String sql = "select game_info.delete_contractor_games("+contractor_games_id+")";
            jdbcTemplate.execute(sql);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }
}
