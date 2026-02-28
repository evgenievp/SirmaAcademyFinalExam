package com.FinalExam.SirmaFinalExam.CSVDrivers;

import com.FinalExam.SirmaFinalExam.Models.Players;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class PlayersCSVReader {

    private List<Players> playersList;

    private final String path = "src/main/resources/csv_files/players.csv";

    public PlayersCSVReader() {
        this.playersList = new LinkedList<>();
        this.playersList = this.readPlayers();
    }


    private List<Players> readPlayers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String read = null;
            try {
                read = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while ((read = reader.readLine()) != null) {
                String[] newPlayer = read.split(",");
                // parsing player data
                // //id, teamNumber, position, fullname,teamid
                int id = Integer.parseInt(newPlayer[0]);
                int teamNumber = Integer.parseInt(newPlayer[1]);
                String position = newPlayer[2];
                String fullName = newPlayer[3];
                int teamId = Integer.parseInt(newPlayer[4]);
                this.playersList.add(
                        new Players(
                                id,
                                teamNumber,
                                position,
                                fullName,
                                teamId
                        )
                );
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }

        return playersList;
    }

    public List<Players> getPlayersList() {
        return playersList;
    }
}
