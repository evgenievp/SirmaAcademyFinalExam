package com.FinalExam.SirmaFinalExam.Controllers;

import com.FinalExam.SirmaFinalExam.Models.Matches;
import com.FinalExam.SirmaFinalExam.Models.Players;
import com.FinalExam.SirmaFinalExam.Models.Records;
import com.FinalExam.SirmaFinalExam.Models.Teams;
import com.FinalExam.SirmaFinalExam.Services.MainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/")
    public List<Players> getAllPlayers() {
        return this.mainService.getPlayers();
    }

    @GetMapping("/teams")
    public List<Teams> getAllTeams() {
        return this.mainService.getTeams();
    }

    @GetMapping("/records")
    public List<Records> getAllRecords() {
        return this.mainService.getRecords();
    }

    @GetMapping("/matches")
    public List<Matches> getAllMatches() {
        return this.mainService.getMatches();
    }
}
