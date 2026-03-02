package com.FinalExam.SirmaFinalExam.CSVDrivers;

import com.FinalExam.SirmaFinalExam.Models.Teams;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class TeamsCSVReader {

    private List<Teams> teamsList;
    private final String path = "src/main/resources/csv_files/teams.csv";

    public TeamsCSVReader() {
        this.teamsList = new LinkedList<>();
        this.teamsList = this.readTeams();
    }

    private List<Teams> readTeams() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String read = null;
            try {
                read = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while ((read = reader.readLine()) != null) {
                String[] newTeam = read.split(",");
                // parse csv teams to team Models
                // id, name, managerFullName, Group
                int id = Integer.parseInt(newTeam[0]);
                String name = newTeam[1];
                String managerFullName = newTeam[2];
                String wholeText = newTeam[3];
                char group = wholeText.charAt(0);
                this.teamsList.add(
                        new Teams(
                                name,
                                managerFullName,
                                group)
                );

            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this.teamsList;
    }

    public List<Teams> getTeamsList() {
        return teamsList;
    }

}
