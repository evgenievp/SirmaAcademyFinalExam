package com.FinalExam.SirmaFinalExam.Services;

import com.FinalExam.SirmaFinalExam.CSVDrivers.RecordsCSVReader;
import com.FinalExam.SirmaFinalExam.Dtos.RecordsDto;
import com.FinalExam.SirmaFinalExam.Models.Records;
import com.FinalExam.SirmaFinalExam.Repos.RecordsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordsService {

    private final RecordsCSVReader recordsCSVReader;
    private final RecordsRepo recordsRepo;

    public RecordsService(RecordsCSVReader recordsCSVReader, RecordsRepo recordsRepo) {
        this.recordsCSVReader = recordsCSVReader;
        this.recordsRepo = recordsRepo;
    }


    public List<Records> getRecords() {
        return this.recordsCSVReader.getRecordsList();
    }

    public void saveAllRecords() {
        this.recordsRepo.saveAll(getRecords());
    }

    public void deleteAllRecords() {
        this.recordsRepo.deleteAll();
    }

    public RecordsDto convertRecordToDto(Records record) {
        return new RecordsDto(
                record.getPlayerId(),
                record.getMatchId(),
                record.getFromMinutes(),
                record.getToMinutes()
        );
    }

    public Records convertDtoToRecord(RecordsDto dto) {
        return new Records(
                dto.getPlayerId(),
                dto.getMatchId(),
                dto.getFromMinutes(),
                dto.getToMinutes()
        );
    }

}
