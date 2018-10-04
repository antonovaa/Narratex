package ru.gameserver.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gameserver.dao.Daoinsert;
import ru.gameserver.model.CrashInfoModel;
import ru.gameserver.model.GameInfoModel;
import ru.gameserver.model.GameReportLog;
import ru.gameserver.model.SingleCrashModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
public class LogController {

    private final Daoinsert daoinsert;

    @Autowired
    public LogController(Daoinsert daoinsert) {
        this.daoinsert = daoinsert;

    }

    @RequestMapping("/game-log")
    @ResponseBody
    public String game() {
        return "  game-log  ";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String index() {
        return "index";
    }

    @RequestMapping("/saveGameInfoList/{name}/{macAddr}")
    @ResponseBody
    public Map<String, String> saveGameInfoList(@RequestBody String gameInfoList,
                                                 @PathVariable("name") String name,
                                                 @PathVariable("macAddr") String macAddr) {
        Type listType = new TypeToken<ArrayList<GameInfoModel>>() {
        }.getType();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("result",""+daoinsert.saveGameInfo(new Gson().fromJson(gameInfoList, listType),name,macAddr));
        return hashMap;
    }

    @RequestMapping("/saveCrashInfo/{name}/{macAddr}")
    @ResponseBody
    public Map<String, String> saveCrashInfo(@RequestBody String gameInfoList,
                                              @PathVariable("name") String name,
                                              @PathVariable("macAddr") String macAddr) {
        Type listType = new TypeToken<ArrayList<CrashInfoModel>>() {
        }.getType();
        HashMap<String, String> hashMap = new HashMap<>();
        if (daoinsert.saveCrashInfo(new Gson().fromJson(gameInfoList, listType),name,macAddr)) {
            hashMap.put("result", "0");//success
        } else {
            hashMap.put("result", "1");//fail
        }
        return hashMap;
    }

    @RequestMapping(value="/singleLog",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String singleLog(@RequestBody String str, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        Gson gson=new Gson();
        GameReportLog gameReportLog=gson.fromJson(str,GameReportLog.class);
        return daoinsert.saveLogGame(gameReportLog);
    }

    @RequestMapping(value="/singleCrash",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public boolean singleCrash(@RequestBody String str, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        Gson gson=new Gson();
        SingleCrashModel singleCrashModel=gson.fromJson(str,SingleCrashModel.class);
        return daoinsert.saveSingleGameCrash(singleCrashModel);
    }
}
