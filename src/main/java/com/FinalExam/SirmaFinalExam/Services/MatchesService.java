package com.FinalExam.SirmaFinalExam.Services;

import com.FinalExam.SirmaFinalExam.CSVDrivers.MatchesCSVReader;
import com.FinalExam.SirmaFinalExam.Dtos.MatchDto;
import com.FinalExam.SirmaFinalExam.Models.Matches;
import com.FinalExam.SirmaFinalExam.Repos.MatchesRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.Optional;

@Service
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

    public void addMatch(MatchDto dto) {
        Matches match = convertDtoToMatch(dto);
        this.matchesRepo.save(match);
    }

    public MatchDto findMatchById(int id) {
        Optional<Matches> opt = this.matchesRepo.findById(id);
        if (opt.isEmpty()) {
            throw new EntityNotFoundException("No entity in db with this id");
        }
        else {
            return convertMatchToDto(opt.get());
        }
    }

    public void deleteMatch(int id) {
        this.matchesRepo.deleteById(id);
    }

    public void editMatch(MatchDto dto, int id) {
        Optional<Matches> match = this.matchesRepo.getMatchById(id);
        if (match.isEmpty()) {
            throw new EntityNotFoundException("Entity with id isn't in this db");
        }
        Matches matchEntity = match.get();
        matchEntity.setATeamId(dto.getATeamId());
        matchEntity.setAwayTeamGoals(dto.getAwayScore());
        matchEntity.setBTeamId(dto.getBTeamId());
        matchEntity.setDate(dto.getDate());
        matchEntity.setHomeTeamGoals(dto.getHomeScore());
        matchEntity.setAwayTeamGoals(dto.getAwayScore());
        this.matchesRepo.save(matchEntity);

    }
}
