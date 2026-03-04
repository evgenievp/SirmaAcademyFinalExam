package com.FinalExam.SirmaFinalExam.Controllers;

import com.FinalExam.SirmaFinalExam.CSVDrivers.TeamsCSVReader;
import com.FinalExam.SirmaFinalExam.Dtos.TeamDto;
import com.FinalExam.SirmaFinalExam.Models.Teams;
import com.FinalExam.SirmaFinalExam.Services.TeamsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity addTeam(@Valid @RequestBody TeamDto team) {
        this.teamsService.addTeam(team);
        return ResponseEntity.status(201).body("Team added to db");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable int id) {
        return ResponseEntity.status(200).body(this.teamsService.findTeamById(id));
    }


    @PatchMapping("/edit/{id}")
    public ResponseEntity<TeamDto> editTeam(@Valid @RequestBody TeamDto dto, @PathVariable int id) {
        this.teamsService.editTeam(dto, id);
        return ResponseEntity.status(200).body(this.teamsService.findTeamById(id));
    }

    private TeamDto convertTeamToDto(Teams team) {
        return new TeamDto(team.getName(),
                team.getManagerFullName(),
                team.getTeamGroup());
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity deleteTeamById(@PathVariable int id) {
        TeamDto dto = this.teamsService.findTeamById(id);
        this.teamsService.deleteTeamById(id);
        return ResponseEntity.status(200).body(dto);
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
