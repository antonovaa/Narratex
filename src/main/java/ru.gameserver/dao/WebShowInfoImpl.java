package ru.gameserver.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class WebShowInfoImpl implements WebShowInfo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WebShowInfoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Map<String, Object>> getGamesLog(String date_begin,String date_end,int contractor_id,int game_id) {

        List<Map<String, Object>> gameInfoModels;
        String gameName="";
        try {
            gameName = jdbcTemplate.queryForList("select g.game_name from game_info.games g where g.games_id=" + game_id, String.class).get(0);
        }
        catch (Exception ignored){
        }
        String sql = "Select * from game_info.get_log_game(?,?,?,?,?)";
        gameInfoModels=jdbcTemplate.queryForList(sql,gameName, date_begin,date_end,contractor_id,game_id);

        return gameInfoModels;
    }

    @Override
    public List<Map<String, Object>> getGamesCrash(String date_begin,String date_end,int contractor_id,int game_id) {
        List<Map<String, Object>> gameInfoModels;
        String gameName="";
        try {
            gameName = jdbcTemplate.queryForList("select * from game_info.games g where g.games_id=" + game_id, String.class).get(0);
        }
        catch (Exception ignored){
        }
        String sql = "Select * from game_info.get_crash_game(?,?,?,?,?)";
        gameInfoModels=jdbcTemplate.queryForList(sql,gameName,date_begin,date_end,contractor_id,game_id);
        return gameInfoModels;
    }

    @Override
    public List<Map<String, Object>> getContractors() {
        try {
            return jdbcTemplate.queryForList("select * from game_info.contractor c");
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getGames() {
        try {
            return jdbcTemplate.queryForList("select g.games_id,g.game_name from game_info.games g");
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> showContractorGames(int contractorId) {
        try {
            return jdbcTemplate.queryForList("select * from game_info.get_contractor_games(" + contractorId + ")");
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> showInitGames(int contractorId) {
        try {
            return jdbcTemplate.queryForList("select * from game_info.get_init_contractor_game(?)",contractorId);
        } catch (Exception e) {
            return null;
        }
    }
}
