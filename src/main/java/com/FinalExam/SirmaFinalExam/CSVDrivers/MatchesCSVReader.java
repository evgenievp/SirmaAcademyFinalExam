package com.FinalExam.SirmaFinalExam.CSVDrivers;

import com.FinalExam.SirmaFinalExam.Models.Matches;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MatchesCSVReader {

    private final String path = "src/main/resources/csv_files/matches.csv";
    private List<Matches> matchesList;

    public MatchesCSVReader() {
        this.matchesList = new LinkedList<>();
        this.matchesList = getAllMatches();

    }

    private List<Matches> getAllMatches() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            String newMatch = null;
            try {
                newMatch = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while ((newMatch = reader.readLine()) != null) {
                // Parse matches csv file into models;
                // ID,ATeamID,BTeamID,Date,Score
                String[] match = newMatch.split(",");
                int id = Integer.parseInt(match[0]);
                int aTeamId = Integer.parseInt(match[1]);
                int bTeamId = Integer.parseInt(match[2]);
                String dateStr = match[3];
                LocalDate date = parseDate(dateStr);


                String[] goals = match[4].split("-");
                int[] results = new int[2];

                boolean callMethod = false;

                for (var ss : goals) {
                    if (ss.contains("(") || ss.contains(")")) {
                        callMethod = true;
                        break;
                    }
                }
                int homeScore = 0;
                int awayScore = 0;
                if (callMethod) {
                    callMethod = !callMethod;
                    results = castResultFrom(goals);
                    homeScore = results[0];
                    awayScore = results[1];
                }
                else {

                    homeScore = Integer.parseInt(goals[0]);
                    awayScore = Integer.parseInt(goals[1]);
                }

                this.matchesList.add(new Matches(
                        aTeamId,
                        bTeamId,
                        date,
                        homeScore,
                        awayScore
                ));
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }

        return this.matchesList;
    }

    private int[] castResultFrom(String[] result) {
        // 0(3)-0(0)
        // 0(3)
        String homeTeam = result[0];
        String awayTeam = result[1];

        int[] results = new int[2];
        results[0] = getResultFromPenalties(homeTeam);
        results[1] = getResultFromPenalties(awayTeam);

        return results;
    }

    private LocalDate parseDate(String dateString) {
        Pattern pattern = Pattern.compile("(0?[1-9]|1[0-2]).(0?[1-9]|[12][0-9]|3[01]).(20[0-9]{2})");

        Matcher matcher = pattern.matcher(dateString.trim());
        if (matcher.matches()) {
            int month = Integer.parseInt(matcher.group(1));
            int day = Integer.parseInt(matcher.group(2));
            int year = Integer.parseInt(matcher.group(3));

            return LocalDate.of(year, month, day);
        }
        else {
            throw new IllegalArgumentException("Invalid format");
        }
    }


    public List<Matches> getMatchesList() {
        return matchesList;
    }

    public int getResultFromPenalties(String result) {
        int leftBracket = result.indexOf("(");
        int rightBracket = result.indexOf(")");
        String regularTime = result.substring(0,leftBracket);
        int regularGoals = Integer.parseInt(regularTime);
        String goalFromPenalties = result.substring(leftBracket + 1, rightBracket);
        int allGoals = regularGoals + Integer.parseInt(goalFromPenalties);
        return allGoals;
    }
}
