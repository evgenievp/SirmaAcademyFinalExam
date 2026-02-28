package com.FinalExam.SirmaFinalExam.Controllers;

import com.FinalExam.SirmaFinalExam.Models.Players;
import com.FinalExam.SirmaFinalExam.Services.PlayersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayersController {
    private final PlayersService playersService;

    public PlayersController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Players>> getAllPlayers() {
        return ResponseEntity.ok().body(this.playersService.getPlayers());
    }

    @PostMapping("/saveAll")
    public ResponseEntity saveAllPlayers() {
        this.playersService.saveAllPlayers();;
        return ResponseEntity.status(201).body("All players are saved");
    }

    @PostMapping("/deleteAll")
    public ResponseEntity deleteAllPlayers() {
        this.playersService.deleteAllPlayers();
        return ResponseEntity.status(200).body("all players are deleted");
    }

}
