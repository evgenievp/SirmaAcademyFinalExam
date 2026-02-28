package com.FinalExam.SirmaFinalExam.Controllers;

import com.FinalExam.SirmaFinalExam.CSVDrivers.MatchesCSVReader;
import com.FinalExam.SirmaFinalExam.Models.Matches;
import com.FinalExam.SirmaFinalExam.Services.MatchesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/matches")
public class MatchesController {
    private final MatchesService matchesService;
    private final MatchesCSVReader matchesCSVReader;

    public MatchesController(MatchesService matchesService, MatchesCSVReader matchesCSVReader) {
        this.matchesService = matchesService;
        this.matchesCSVReader = matchesCSVReader;
    }

    @GetMapping("/matches")
    public List<Matches> getAllMatches() {
        return this.matchesService.getMatches();
    }

    @PostMapping("saveAll/Matches")
    public ResponseEntity saveAllMatches() {
        this.matchesService.saveAllMatches();
        return ResponseEntity.status(201).body("matches are saved");
    }


    @PostMapping("deleteAll/matches")
    public ResponseEntity deleteAllMatches() {
        this.matchesService.deleteAllMatches();
        return ResponseEntity.ok("all matches are deleted");
    }
}
