package ru.gameserver.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.gameserver.model.Gkey;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContractorGameImpl implements ContractorGame {

    String KEYFORONCECONSTANTINE = "KeyGeneratedForOnceContractor";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContractorGameImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public String getKeyToday(int contractor_games_id) {

        String sql = "select * from game_info.get_contractor_game_place(" + contractor_games_id + ")";
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForList(sql).get(0);
        Date date = new Date();
        String contGame = (String) stringObjectMap.get("name") + (String) stringObjectMap.get("game_name");
        return getHash(contGame + date.getMonth() + date.getDate());
    }

    @Override
    public String getHash(String str) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");

            md.update(str.getBytes());
            byte[] dataBytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < dataBytes.length; i++) {
                sb.append(Integer.toString((dataBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }


    @Override
    public String getExistMd5Key(String macAddress, String gameName) {
        try {
            String sql = "select * from game_info.get_key_exist_init_game(?,?)";
            return jdbcTemplate.queryForList(sql, new Object[]{macAddress, gameName}, String.class).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String InitSaveGkey(Gkey gkey) {

        boolean ifExist = false;
        int contractor_games_id = -1;
        int id = -1;

        String existKey;
        existKey = getExistMd5Key(gkey.getMac(), gkey.getGameName());
        if (existKey == null) {
            for (Map<String, Object> map : getAllMd5Keys()) {
                if (map.get("key_game").equals(gkey.getKey())) {
                    contractor_games_id = (int) map.get("contractor_games_id");
                    id = (int) map.get("id");
                    ifExist = true;
                }
            }
            if (!ifExist) {
                return "this key not generated";
            }
            try {
                String sql = "insert into game_info.init_contractor_game_mac_key (key_game, place_game, mac_address, contractor_games_id) values ('" + gkey.getKey() + "','" + gkey.getPlace() + "','" + gkey.getMac() + "'," + contractor_games_id + ")";
                jdbcTemplate.execute(sql);
                decreaseCountKey(id);
                return "success init game";
            } catch (Exception e) {
                return "error init game";
            }
        } else if (existKey.equals(gkey.getKey())) {
            if (!jdbcTemplate.queryForList("select game_info.is_allowed_this_pc_contractor_game(?,?)", new Object[]{gkey.getMac(), gkey.getGameName()}, Boolean.class).get(0)) {
                return "error init game";
            }
            return "success init game";
        } else {
            return "this PC already used key";
        }
    }

    @Override
    public String changeResolution(int init_contractor_game_mac_key_id) {

        try {

            jdbcTemplate.execute("update game_info.init_contractor_game_mac_key set isallowed=not isallowed where init_contractor_game_mac_key_id=" + init_contractor_game_mac_key_id);

            return "success";
        } catch (Exception e) {
            return "error";
        }
    }


    @Override
    public Map<String, Object> getMd5Key(int contractor_games_id) {
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        String sql = "select key_game,count from game_info.generated_contractor_key where count>0 and contractor_games_id=" + contractor_games_id;
        try {

            return jdbcTemplate.queryForList(sql).get(0);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public List<Map<String, Object>> getAllMd5Keys() {

        String sql = "select key_game,contractor_games_id,id from game_info.generated_contractor_key where count>0";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public String generateAndSaveMd5Key(int contractor_games_id, int count,boolean isConstantine) {
        String keyG;
        if(isConstantine){
            keyG = KEYFORONCECONSTANTINE;
        }
        else {
            keyG=getKeyToday(contractor_games_id);
        }
        String sql = "insert into game_info.generated_contractor_key (key_game, contractor_games_id, count) values ('" + keyG + "'," + contractor_games_id + "," + count + ");";
        try {
            jdbcTemplate.execute(sql);
            return keyG;
        } catch (Exception e) {
            return "error";
        }
    }

    @Override
    public boolean decreaseCountKey(int id) {

        String sql = "update game_info.generated_contractor_key set count=count-1 where id=" + id;
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
