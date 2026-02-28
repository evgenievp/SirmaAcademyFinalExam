package com.FinalExam.SirmaFinalExam.Controllers;

import com.FinalExam.SirmaFinalExam.CSVDrivers.TeamsCSVReader;
import com.FinalExam.SirmaFinalExam.Models.Teams;
import com.FinalExam.SirmaFinalExam.Services.TeamsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamsController {
    private final TeamsService teamsService;
    private final TeamsCSVReader teamsCSVReader;

    public TeamsController(TeamsService teamsService, TeamsCSVReader teamsCSVReader) {
        this.teamsService = teamsService;
        this.teamsCSVReader = teamsCSVReader;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Teams>> getAllTeams() {
        return ResponseEntity.status(200).body(this.teamsService.getTeams());
    }

    @PostMapping("/saveAll")
    public ResponseEntity saveAllTeams() {
        this.teamsService.saveAllTeams();
        return ResponseEntity.status(201).body("All teams are saved into db");
    }

    @PostMapping("/deleteAll")
    public ResponseEntity deleteAllTeams() {
        this.teamsService.deleteAllTeams();
        return ResponseEntity.status(200).body("all teams are deleted");
    }

}
