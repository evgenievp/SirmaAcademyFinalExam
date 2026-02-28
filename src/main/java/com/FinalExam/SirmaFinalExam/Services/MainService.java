package com.FinalExam.SirmaFinalExam.Services;

import com.FinalExam.SirmaFinalExam.CSVDrivers.MatchesCSVReader;
import com.FinalExam.SirmaFinalExam.CSVDrivers.PlayersCSVReader;
import com.FinalExam.SirmaFinalExam.CSVDrivers.RecordsCSVReader;
import com.FinalExam.SirmaFinalExam.CSVDrivers.TeamsCSVReader;
import com.FinalExam.SirmaFinalExam.Models.Matches;
import com.FinalExam.SirmaFinalExam.Models.Players;
import com.FinalExam.SirmaFinalExam.Models.Records;
import com.FinalExam.SirmaFinalExam.Models.Teams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    PlayersCSVReader playersCSVReader;
    TeamsCSVReader teamsCSVReader;
    RecordsCSVReader recordsCSVReader;
    MatchesCSVReader matchesCSVReader;

    public MainService(PlayersCSVReader playersCSVReader,
                       TeamsCSVReader teamsCSVReader,
                       RecordsCSVReader recordsCSVReader,
                       MatchesCSVReader matchesCSVReader) {
        this.playersCSVReader = playersCSVReader;
        this.teamsCSVReader = teamsCSVReader;
        this.recordsCSVReader = recordsCSVReader;
        this.matchesCSVReader = matchesCSVReader;
    }

    public List<Players> getPlayers() {
        return this.playersCSVReader.getPlayersList();
    }

    public List<Teams> getTeams() {
        return this.teamsCSVReader.getTeamsList();
    }

    public List<Records> getRecords() {
        return this.recordsCSVReader.getRecordsList();
    }


    public List<Matches> getMatches() {
        return this.matchesCSVReader.getMatchesList();
    }
}
