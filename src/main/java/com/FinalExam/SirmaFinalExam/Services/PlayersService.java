package com.FinalExam.SirmaFinalExam.Services;

import com.FinalExam.SirmaFinalExam.CSVDrivers.PlayersCSVReader;
import com.FinalExam.SirmaFinalExam.Dtos.PlayerDto;
import com.FinalExam.SirmaFinalExam.Models.Players;
import com.FinalExam.SirmaFinalExam.Repos.PlayersRepo;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
