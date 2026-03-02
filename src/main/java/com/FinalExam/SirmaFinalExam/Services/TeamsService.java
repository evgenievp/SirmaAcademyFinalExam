package com.FinalExam.SirmaFinalExam.Services;

import com.FinalExam.SirmaFinalExam.CSVDrivers.TeamsCSVReader;
import com.FinalExam.SirmaFinalExam.Dtos.TeamDto;
import com.FinalExam.SirmaFinalExam.Models.Teams;
import com.FinalExam.SirmaFinalExam.Repos.TeamsRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamsService {
    private final TeamsRepo teamsRepo;
    private final TeamsCSVReader teamsCSVReader;

    public TeamsService(TeamsRepo teamsRepo, TeamsCSVReader teamsCSVReader) {
        this.teamsRepo = teamsRepo;
        this.teamsCSVReader = teamsCSVReader;
    }

    public void addTeam(TeamDto team) {
        this.teamsRepo.save(convertDtoToTeam(team));
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

    public Teams findTeamById(int id) {
        Optional<Teams> team = this.teamsRepo.findById(id);
        if (team.isEmpty()) {
            throw new EntityNotFoundException("Team with that id isn't in this db");
        }
        return team.get();
    }

    public void editTeam(TeamDto dto, int id) {
        Optional<Teams> team = this.teamsRepo.findById(id);
        if (team.isEmpty()) {
            throw new EntityNotFoundException("Can't edit team with that id");
        }
        Teams teamEntity = team.get();
        teamEntity.setManagerFullName(dto.getManagerFullName());
        teamEntity.setName(dto.getName());
        teamEntity.setteamGroup(dto.getTeamGroup());
        this.teamsRepo.save(teamEntity);
    }
}
