package com.FinalExam.SirmaFinalExam.Controllers;

import com.FinalExam.SirmaFinalExam.Services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    private final MainService mainService;
    private final PlayersService playersService;
    private final TeamsService teamsService;
    private final RecordsService recordsService;
    private final MatchesService matchesService;

    public MainController(MainService mainService,
                          PlayersService playersService, TeamsService teamsService, RecordsService recordsService, MatchesService matchesService) {
        this.mainService = mainService;
        this.playersService = playersService;
        this.teamsService = teamsService;
        this.recordsService = recordsService;
        this.matchesService = matchesService;
    }

    @GetMapping("/pair-played-most-time-info")
    public ResponseEntity<List<Object[]>> getPlayersPair() {
        return ResponseEntity.status(200).body(this.mainService.getMostTimePlayedPlayersPair());
    }

    @PostMapping("/initAll")
    public ResponseEntity saveEverything() {
        this.playersService.saveAllPlayers();
        this.teamsService.saveAllTeams();
        this.recordsService.saveAllRecords();
        this.matchesService.saveAllMatches();
        return ResponseEntity.status(201).body("Everything is now on db");
    }


    @PostMapping("/deleteAll")
    public ResponseEntity deleteEverything() {
        playersService.deleteAllPlayers();
        teamsService.deleteAllTeams();
        matchesService.deleteAllMatches();
        recordsService.deleteAllRecords();
        return ResponseEntity.status(200).body("Everything is deleted");
    }


}
