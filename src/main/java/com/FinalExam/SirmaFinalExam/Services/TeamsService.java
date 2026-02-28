package com.FinalExam.SirmaFinalExam.Services;

import com.FinalExam.SirmaFinalExam.CSVDrivers.TeamsCSVReader;
import com.FinalExam.SirmaFinalExam.Dtos.TeamDto;
import com.FinalExam.SirmaFinalExam.Models.Teams;
import com.FinalExam.SirmaFinalExam.Repos.TeamsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamsService {
    private final TeamsRepo teamsRepo;
    private final TeamsCSVReader teamsCSVReader;

    public TeamsService(TeamsRepo teamsRepo, TeamsCSVReader teamsCSVReader) {
        this.teamsRepo = teamsRepo;
        this.teamsCSVReader = teamsCSVReader;
    }


    public void saveAllTeams() {
        this.teamsRepo.saveAll(getTeams());
    }

    public void deleteAllTeams() {
        this.teamsRepo.deleteAll();
    }


    public List<Teams> getTeams() {
        return this.teamsCSVReader.getTeamsList();
    }

    public TeamDto convertTeamToDto(Teams team) {
        return new TeamDto(
                team.getName(),
                team.getManagerFullName(),
                team.getteamGroup()
        );
    }

    public Teams convertDtoToTeam(TeamDto dto) {
        return new Teams(
                dto.getName(),
                dto.getManagerFullName(),
                dto.getTeamGroup()
        );
    }

}
