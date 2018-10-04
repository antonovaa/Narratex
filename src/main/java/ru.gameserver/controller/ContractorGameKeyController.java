package ru.gameserver.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gameserver.dao.ContractorGameImpl;
import ru.gameserver.model.Gkey;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ContractorGameKeyController {

    private final ContractorGameImpl contractorGame;

    @Autowired
    public ContractorGameKeyController(ContractorGameImpl contractorGame) {
        this.contractorGame = contractorGame;
    }

    @RequestMapping(value = "/add/{type}")
    @ResponseBody
    public String showList(@PathVariable(value = "type") String type) {
        return "";
    }

    @RequestMapping("/init")
    @ResponseBody
    public String init(@RequestParam(value = "initAdmin") String initAdmin) {
        Gkey gkey = new Gson().fromJson(initAdmin, Gkey.class);
        return contractorGame.InitSaveGkey(gkey);
    }

    @RequestMapping(value = "/singleInit",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String singleInit(@RequestBody String strInit) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        Gkey gkey = new Gson().fromJson(strInit, Gkey.class);
        return contractorGame.InitSaveGkey(gkey);
    }

    @RequestMapping(value = "/getMd5Key")
    @ResponseBody
    public Map<String,Object> getMd5Key(@RequestParam int contractor_games_id) {

        return contractorGame.getMd5Key(contractor_games_id);
    }

    @RequestMapping(value = "/generateAndSaveMd5Key")
    @ResponseBody
    public String generateAndSaveMd5Key(@RequestParam int contractor_games_id,@RequestParam int count,@RequestParam boolean isConstantine) {

        return contractorGame.generateAndSaveMd5Key(contractor_games_id,count,isConstantine);
    }

    @RequestMapping(value = "/changeResolution")
    @ResponseBody
    public String changeResolution(@RequestParam int init_contractor_game_mac_key_id) {

        return contractorGame.changeResolution(init_contractor_game_mac_key_id);
    }

}
