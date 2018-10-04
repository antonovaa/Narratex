package ru.gameserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.gameserver.dao.WebSaveInfoImpl;

@Controller
public class WebAddController {

    private final WebSaveInfoImpl webSaveInfo;

    @Autowired
    public WebAddController(WebSaveInfoImpl webSaveInfo) {
        this.webSaveInfo = webSaveInfo;
    }


    @RequestMapping(value = "/addGame")
    @ResponseBody
    public String addGame(@RequestParam("gameName") String gameName) {

        if (webSaveInfo.addGame(gameName)) {
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/addContractor")
    @ResponseBody
    public String addContractor(@RequestParam("contractorName") String contractorName,
                                @RequestParam("contractorPlace") String contractorPlace) {

        if (webSaveInfo.addContractor(contractorName,contractorPlace)) {
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/addContractorGames")
    @ResponseBody
    public String addContractorGames(@RequestParam("contractor_id") int contractorId,
                                @RequestParam("games_id") int gameId) {

        if (webSaveInfo.addContractorGames(contractorId,gameId)) {
            return "success";
        } else {
            return "error";
        }
    }
}