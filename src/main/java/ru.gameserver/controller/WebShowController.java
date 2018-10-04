package ru.gameserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.gameserver.dao.WebShowInfoImpl;

import java.util.List;
import java.util.Map;

@Controller
public class WebShowController {


    private final WebShowInfoImpl webShowInfo;

    @Autowired
    public WebShowController(WebShowInfoImpl webShowInfo) {
        this.webShowInfo = webShowInfo;
    }


    @RequestMapping(value = "/showLogList")@ResponseBody
    public List<Map<String, Object>> showList( @RequestParam(value = "date_begin") String date_begin,
                                              @RequestParam(value = "date_end") String date_end,
                                              @RequestParam(value = "contractor_id") int contractor_id,
                                              @RequestParam(value = "game_id") int game_id

    )
    {
        try {
            return webShowInfo.getGamesLog(date_begin, date_end,contractor_id,game_id);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/showCrashList")    @ResponseBody
    public List<Map<String, Object>> showCrashList(@RequestParam(value = "date_begin") String date_begin ,
                                                   @RequestParam(value = "date_end") String date_end,
                                                   @RequestParam(value = "contractor_id") int contractor_id,
                                                   @RequestParam(value = "game_id") int game_id

    ) {

        try {
            return webShowInfo.getGamesCrash( date_begin, date_end,contractor_id,game_id);
        } catch (Exception e) {
            return null;
        }
    }


    @RequestMapping(value = "/getGames")    @ResponseBody
    public List<Map<String, Object>> getGames() {
        try {
            return webShowInfo.getGames();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/getContractorGames")    @ResponseBody
    public List<Map<String, Object>>  getContractorGames(@RequestParam int contractor_id) {
        try {
            return webShowInfo.showContractorGames(contractor_id);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/getContractors")
    @ResponseBody
    public List<Map<String, Object>> getContractors() {
        try {
            return webShowInfo.getContractors();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/getInitContractorGame")
    @ResponseBody
    public List<Map<String, Object>> getInitContractorGame(@RequestParam int contractorId) {
        try {
            return webShowInfo.showInitGames(contractorId);
        } catch (Exception e) {
            return null;
        }
    }
}
