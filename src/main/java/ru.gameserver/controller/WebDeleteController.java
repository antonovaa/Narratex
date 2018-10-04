package ru.gameserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.gameserver.dao.WebDeleteImpl;

@Controller
public class WebDeleteController {

    private final WebDeleteImpl webDelete;

    @Autowired
    public WebDeleteController(WebDeleteImpl webDelete) {
        this.webDelete = webDelete;
    }


    @RequestMapping(value = "/deleteGame")
    @ResponseBody
    public String deleteGame(@RequestParam(value = "games_id") int games_id){
        try {
            return webDelete.deleteGames(games_id)?"success":"error";
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/deleteContractor")
    @ResponseBody
    public String deleteContractor(@RequestParam(value = "contractor_id") int contractor_id){
        try {
            return webDelete.deleteContractor(contractor_id)?"success":"error";
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/deleteContractorGames")
    @ResponseBody
    public String deleteContractorGames(@RequestParam(value = "contractor_games_id") int contractor_games_id){
        try {
            return webDelete.deleteContractorGames(contractor_games_id)?"success":"error";
        } catch (Exception e) {
            return null;
        }
    }

}
