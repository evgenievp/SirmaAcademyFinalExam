package com.FinalExam.SirmaFinalExam.CSVDrivers;

import com.FinalExam.SirmaFinalExam.Models.Records;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class RecordsCSVReader {

    private final String path = "src/main/resources/csv_files/records.csv";
    private List<Records> recordsList;


    public RecordsCSVReader(List<Records> recordsList) {
        this.recordsList = recordsList;
        this.recordsList = getAllRecords();
    }

    private List<Records> getAllRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String readRecord = null;
            try {
                readRecord = reader.readLine();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while ((readRecord = reader.readLine()) != null) {
                String[] newRecord = readRecord.split(",");
                // parsing records to models, but shall check for nullable "to minutes"
                // //id, playerId, matchId, fromMinutes, toMinutes
                int id = Integer.parseInt(newRecord[0]);
                int playerId = Integer.parseInt(newRecord[1]);
                int matchId = Integer.parseInt(newRecord[2]);
                int fromMinutes = Integer.parseInt(newRecord[3]);
                String toMinutesText = newRecord[3];
                int toMinutes = 90;
                if (toMinutesText.equals("NULL")) { // copy "NULL" as it written in csv file
                    toMinutes = 90;
                }
                else {
                    toMinutes = Integer.parseInt(toMinutesText);
                }
                this.recordsList.add(
                        new Records(
                                id,
                                playerId,
                                matchId,
                                fromMinutes,
                                toMinutes
                        )
                );
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this.recordsList;
    }

    public List<Records> getRecordsList() {
        return recordsList;
    }
}
