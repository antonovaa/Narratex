package ru.gameserver.dao;

import ru.gameserver.model.CrashInfoModel;
import ru.gameserver.model.GameInfoModel;
import ru.gameserver.model.GameReportLog;
import ru.gameserver.model.SingleCrashModel;

import java.util.List;

public interface Daoinsert {
    public int saveGameInfo(List<GameInfoModel> v, String name, String macAddr);

    public boolean saveCrashInfo(List<CrashInfoModel> crashInfoList, String name, String macAddr);

    public boolean saveSingleGameCrash(SingleCrashModel singleCrashModel);

    public String saveLogGame(GameReportLog gameReportLog);

}
