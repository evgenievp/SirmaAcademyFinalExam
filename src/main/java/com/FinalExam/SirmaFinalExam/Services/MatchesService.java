package com.FinalExam.SirmaFinalExam.Services;

import com.FinalExam.SirmaFinalExam.CSVDrivers.MatchesCSVReader;
import com.FinalExam.SirmaFinalExam.Dtos.MatchDto;
import com.FinalExam.SirmaFinalExam.Models.Matches;
import com.FinalExam.SirmaFinalExam.Repos.MatchesRepo;

import java.util.List;

public class MatchesService {
    private final MatchesRepo matchesRepo;
    private final MatchesCSVReader matchesCSVReader;

    public MatchesService(MatchesRepo matchesRepo, MatchesCSVReader matchesCSVReader) {
        this.matchesRepo = matchesRepo;
        this.matchesCSVReader = matchesCSVReader;
    }


    public List<Matches> getMatches() {
        return this.matchesCSVReader.getMatchesList();
    }

    public void deleteAllMatches() {
        this.matchesRepo.deleteAll();;
    }

    public void saveAllMatches() {
        this.matchesRepo.saveAll(getMatches());
    }

    public MatchDto convertMatchToDto(Matches match) {
        return new MatchDto(
                //ATeamId, BTeamId, Date, Score
                match.getATeamId(),
                match.getBTeamId(),
                match.getDate(),
                match.getHomeTeamGoals(),
                match.getAwayTeamGoals()
        );
    }

    public Matches convertDtoToMatch(MatchDto dto) {
        return new Matches(
                dto.getATeamId(),
                dto.getBTeamId(),
                dto.getDate(),
                dto.getHomeScore(),
                dto.getAwayScore()
        );
    }
}
