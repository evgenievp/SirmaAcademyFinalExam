package com.FinalExam.SirmaFinalExam.Controllers;

import com.FinalExam.SirmaFinalExam.Models.Matches;
import com.FinalExam.SirmaFinalExam.Models.Players;
import com.FinalExam.SirmaFinalExam.Models.Records;
import com.FinalExam.SirmaFinalExam.Models.Teams;
import com.FinalExam.SirmaFinalExam.Services.MainService;
import com.FinalExam.SirmaFinalExam.Services.PlayersService;
import com.FinalExam.SirmaFinalExam.Services.TeamsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    private final MainService mainService;
    private final PlayersService playersService;
    private final TeamsService teamsService;

    public MainController(MainService mainService,
                          PlayersService playersService, TeamsService teamsService) {
        this.mainService = mainService;
        this.playersService = playersService;
        this.teamsService = teamsService;
    }




}
