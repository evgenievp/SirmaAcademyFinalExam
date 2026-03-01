package com.FinalExam.SirmaFinalExam.Services;

import com.FinalExam.SirmaFinalExam.CSVDrivers.PlayersCSVReader;
import com.FinalExam.SirmaFinalExam.Dtos.PlayerDto;
import com.FinalExam.SirmaFinalExam.Models.Players;
import com.FinalExam.SirmaFinalExam.Repos.PlayersRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayersService {
    PlayersCSVReader playersCSVReader;
    PlayersRepo playersRepo;

    public PlayersService(PlayersCSVReader playersCSVReader,
                          PlayersRepo playersRepo) {
        this.playersCSVReader = playersCSVReader;
        this.playersRepo = playersRepo;
    }

    public List<Players> getPlayers() {
        return this.playersCSVReader.getPlayersList();
    }


    public void saveAllPlayers() {
        this.playersRepo.saveAll(getPlayers());
    }

    public void deleteAllPlayers() {
        this.playersRepo.deleteAll();
    }

    private PlayerDto convertPlayerToDto(Players pl) {
        return new PlayerDto(
                pl.getTeamNumber(),
                pl.getPosition(),
                pl.getFullName(),
                pl.getTeamId()
        );
    }

    private Players convertDtoToPlayers(PlayerDto dto) {
        return new Players(
                dto.getTeamNumber(),
                dto.getPosition(),
                dto.getFullname(),
                dto.getTeamId()
        );
    }

    public void addPlayer(PlayerDto dto) {
        this.playersRepo.save(convertDtoToPlayers(dto));
    }

    public PlayerDto findPlayerById(int id) {
        Optional<Players> opt = this.playersRepo.findById(id);
        if (opt.isEmpty()) {
            throw new EntityNotFoundException("No player with that id in db");
        }
        return convertPlayerToDto(opt.get());
    }

    public void deletePlayerById(int id) {
        this.playersRepo.deleteById(id);
    }

    public void editPlayer(PlayerDto dto, int id) {
        Optional<Players> player = this.playersRepo.getPlayerById(id);
        if (player.isEmpty()) {
            throw new EntityNotFoundException("Player with that id isn't in this db");
        }
        Players playerEntity = player.get();
        playerEntity.setFullName(dto.getFullname());
        playerEntity.setPosition(dto.getPosition());
        playerEntity.setTeamId(dto.getTeamId());
        playerEntity.setTeamNumber(dto.getTeamNumber());
        this.playersRepo.save(playerEntity);
    }
}
