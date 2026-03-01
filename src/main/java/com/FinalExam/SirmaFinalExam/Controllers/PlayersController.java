package com.FinalExam.SirmaFinalExam.Controllers;

import com.FinalExam.SirmaFinalExam.Dtos.PlayerDto;
import com.FinalExam.SirmaFinalExam.Models.Players;
import com.FinalExam.SirmaFinalExam.Services.PlayersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayersController {
    private final PlayersService playersService;

    public PlayersController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @PostMapping("/addPlayer")
    public ResponseEntity addPlayer(@RequestBody PlayerDto dto) {
        this.playersService.addPlayer(dto);
        return ResponseEntity.status(201).body("Player is added to db");
    }

    @PostMapping("/deletePlayer/{id}")
    public ResponseEntity<PlayerDto> deletePlayer(@PathVariable int id) {
        PlayerDto player = this.playersService.findPlayerById(id);
        this.playersService.deletePlayerById(id);
        return ResponseEntity.status(200).body(player);
    }

    @PatchMapping("/editPlayer/{id}")
    public ResponseEntity<PlayerDto> editMatch(@PathVariable int id, @RequestBody PlayerDto dto) {
        this.playersService.editPlayer(dto, id);
        return ResponseEntity.status(200).body(this.playersService.findPlayerById(id));
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
