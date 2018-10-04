package ru.gameserver.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.gameserver.model.CrashInfoModel;
import ru.gameserver.model.GameInfoModel;
import ru.gameserver.model.GameReportLog;
import ru.gameserver.model.SingleCrashModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class DaoInsertImpl implements Daoinsert {


    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public DaoInsertImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    @Override
    public int saveGameInfo(List<GameInfoModel> v, String name, String macAddr) {

        int result=0;
        int l = v.size();
        String[] gameStart = new String[l];
        String[] gameEnd = new String[l];
        String[] playStart = new String[l];
        Integer[] playerCountListArr = new Integer[l];
        String[] serverIpListArr = new String[l];

        for (int i = 0; i < l; i++) {
            gameStart[i] = v.get(i).getGameStart();
            gameEnd[i] = v.get(i).getGameEnd();
            playStart[i] = v.get(i).getPlayStart();
            playerCountListArr[i] = v.get(i).getPlayerCount();
            serverIpListArr[i] = v.get(i).getServerIp();
        }

        String callSQL = "select game_info.insert_array_info(?,?,?,?,?,?,?);";
        try (Connection con = dataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(callSQL)) {

            Array ar1 = con.createArrayOf("text", gameStart);
            Array ar2 = con.createArrayOf("text", gameEnd);
            Array ar3 = con.createArrayOf("text", playStart);
            Array ar4 = con.createArrayOf("int4", playerCountListArr);
            Array ar5 = con.createArrayOf("text", serverIpListArr);

            preparedStatement.setArray(1, ar1);
            preparedStatement.setArray(2, ar2);
            preparedStatement.setArray(3, ar3);
            preparedStatement.setArray(4, ar4);
            preparedStatement.setArray(5, ar5);
            preparedStatement.setString(6, macAddr);
            preparedStatement.setString(7, name);
            preparedStatement.execute();
            result = 0;

            try {


                if (!jdbcTemplate.queryForList("select game_info.is_allowed_this_pc_contractor_game(?,?)", new Object[]{macAddr, name}, Boolean.class).get(0)) {
                    result = 2;
                }
            }
            catch (Exception e){

            }
            return result;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return result;
        }
    }

    @Override
    public boolean saveCrashInfo(List<CrashInfoModel> crashInfoList, String name, String macAddr) {

        int l = crashInfoList.size();
        String[] playerIps = new String[l];
        String[] serverIps = new String[l];
        String[] logPlayers = new String[l];
        String[] logServers = new String[l];
        String[] gameStarts = new String[l];

        for (int i = 0; i < l; i++) {
            playerIps[i] = crashInfoList.get(i).getPlayerIp();
            serverIps[i] = crashInfoList.get(i).getServerIp();
            logPlayers[i] = crashInfoList.get(i).getLogPlayer();
            logServers[i] = crashInfoList.get(i).getLogServer();
            gameStarts[i] = crashInfoList.get(i).getGameStart();
        }

        try (Connection con = dataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("select game_info.insert_array_crash_info(?,?,?,?,?,?,?)")) {

            Array arPlayerIds = con.createArrayOf("text", playerIps);
            Array arServerIds = con.createArrayOf("text", serverIps);
            Array arLogPlayers = con.createArrayOf("text", logPlayers);
            Array arLogServers = con.createArrayOf("text", logServers);
            Array arGameStarts = con.createArrayOf("text", gameStarts);

            preparedStatement.setArray(1, arGameStarts);
            preparedStatement.setArray(2, arPlayerIds);
            preparedStatement.setArray(3, arServerIds);
            preparedStatement.setArray(4, arLogServers);
            preparedStatement.setArray(5, arLogPlayers);
            preparedStatement.setString(6, name);
            preparedStatement.setString(7, macAddr);
            preparedStatement.execute();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean saveSingleGameCrash(SingleCrashModel singleCrashModel) {

            String timeStamp=singleCrashModel.getGameStart().split("-")[0].replace('.','-')+" "+singleCrashModel.getGameStart().split("-")[1].replace('.',':');
            String sql="insert into game_info.log_crash_game (game_start,game_name,mac_addr,log_sever) values (?,?,?,?)";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement preparedStatement = con.prepareStatement(sql)) {

                preparedStatement.setTimestamp(1, Timestamp.valueOf(timeStamp));
                preparedStatement.setString(2, singleCrashModel.getGameName());
                preparedStatement.setString(3, singleCrashModel.getMacAddress());
                preparedStatement.setString(4, singleCrashModel.getCrashLog());
                preparedStatement.execute();

                return true;
            } catch (Exception e) {
                return false;
            }


    }


    @Override
    public String saveLogGame(GameReportLog gameReportLog) {
        String result;
        int l = gameReportLog.getLog().size();
        String[] dateStartArr = new String[l];
        String[] dateEndArr = new String[l];
        try {
            for (int i = 0; i < gameReportLog.getLog().size(); i++) {
                dateStartArr[i] = gameReportLog.getLog().get(i).substring(0, 19);
                if (gameReportLog.getLog().get(i).contains("Finish")) {
                    dateEndArr[i] = "Finish " + gameReportLog.getLog().get(i).substring(28, 47);
                } else if (gameReportLog.getLog().get(i).contains("Reset")) {
                    dateEndArr[i] = "Reset " + gameReportLog.getLog().get(i).substring(28, 47);
                } else if (gameReportLog.getLog().get(i).contains("ResetOnTheLastMap")) {
                    dateEndArr[i] = "ResetOnTheLastMap " + gameReportLog.getLog().get(i).substring(28, 47);
                } else {
                    dateEndArr[i] = "restart PC or error";
                }
            }
        } catch (Exception e) {
            return "other problems";
        }

        String callSQL = "select game_info.insert_array_log_play_games(?,?,?,?);";
        try (Connection con = dataSource.getConnection();

             PreparedStatement preparedStatement = con.prepareStatement(callSQL)) {

            Array ar1 = con.createArrayOf("text", dateStartArr);
            Array ar2 = con.createArrayOf("text", dateEndArr);

            preparedStatement.setArray(1, ar1);
            preparedStatement.setArray(2, ar2);
            preparedStatement.setString(3, gameReportLog.getMacAddress());
            preparedStatement.setString(4, gameReportLog.getGameName());
            preparedStatement.execute();

            result = "success get file to backend";

            if (!jdbcTemplate.queryForList("select game_info.is_allowed_this_pc_contractor_game(?,?)", new Object[]{gameReportLog.getMacAddress(), gameReportLog.getGameName()}, Boolean.class).get(0)) {
                result="success get file to backend and block";
            }
            return result;

        } catch (SQLException e1) {
            e1.printStackTrace();
            return "error save log";
        }
    }
}
