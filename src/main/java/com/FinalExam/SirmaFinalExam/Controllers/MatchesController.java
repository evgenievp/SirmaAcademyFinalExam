package com.FinalExam.SirmaFinalExam.Controllers;

import com.FinalExam.SirmaFinalExam.CSVDrivers.MatchesCSVReader;
import com.FinalExam.SirmaFinalExam.Dtos.MatchDto;
import com.FinalExam.SirmaFinalExam.Models.Matches;
import com.FinalExam.SirmaFinalExam.Services.MatchesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/matches")
public class MatchesController {
    private final MatchesService matchesService;
    private final MatchesCSVReader matchesCSVReader;

    public MatchesController(MatchesService matchesService, MatchesCSVReader matchesCSVReader) {
        this.matchesService = matchesService;
        this.matchesCSVReader = matchesCSVReader;
    }

    @PostMapping("/add")
    public ResponseEntity addMatch(@RequestBody MatchDto dto) {
        this.matchesService.addMatch(dto);
        return ResponseEntity.status(201).body("Match is added to db");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MatchDto> getMatchById(@PathVariable int id) {
        return ResponseEntity.status(200).body(this.matchesService.findMatchById(id));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<MatchDto> deleteMatch(@Valid @PathVariable int id) {
        MatchDto match = this.matchesService.findMatchById(id);
        this.matchesService.deleteMatch(id);
        return ResponseEntity.status(200).body(match);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity editMatch(@Valid @PathVariable int id, @RequestBody MatchDto dto) {
        this.matchesService.editMatch(dto, id);
        return ResponseEntity.status(200).body(this.matchesService.findMatchById(id));
    }

    @GetMapping("/getAll")
    public List<Matches> getAllMatches() {
        return this.matchesService.getMatches();
    }

    @PostMapping("/saveAll")
    public ResponseEntity saveAllMatches() {
        this.matchesService.saveAllMatches();
        return ResponseEntity.status(201).body("matches are saved");
    }


    @PostMapping("/deleteAll")
    public ResponseEntity deleteAllMatches() {
        this.matchesService.deleteAllMatches();
        return ResponseEntity.ok("all matches are deleted");
    }
}
