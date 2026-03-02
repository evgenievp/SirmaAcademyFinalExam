package com.FinalExam.SirmaFinalExam.Controllers;

import com.FinalExam.SirmaFinalExam.Dtos.PlayerDto;
import com.FinalExam.SirmaFinalExam.Models.Players;
import com.FinalExam.SirmaFinalExam.Services.PlayersService;
import jakarta.validation.Valid;
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

    @PostMapping("/add")
    public ResponseEntity addPlayer(@Valid @RequestBody PlayerDto dto) {
        this.playersService.addPlayer(dto);
        return ResponseEntity.status(201).body("Player is added to db");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<PlayerDto> deletePlayer(@PathVariable int id) {
        PlayerDto player = this.playersService.findPlayerById(id);
        this.playersService.deletePlayerById(id);
        return ResponseEntity.status(200).body(player);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<PlayerDto> editMatch(@Valid @RequestBody PlayerDto dto, @PathVariable int id) {
        this.playersService.editPlayer(dto, id);
        return ResponseEntity.status(200).body(this.playersService.findPlayerById(id));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable int id) {
        return ResponseEntity.status(200).body(this.playersService.getPlayerById(id));
    }

    @GetMapping("/getAll")
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
