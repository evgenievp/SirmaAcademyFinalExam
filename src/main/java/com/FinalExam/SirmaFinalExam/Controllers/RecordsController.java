package com.FinalExam.SirmaFinalExam.Controllers;

import com.FinalExam.SirmaFinalExam.CSVDrivers.RecordsCSVReader;
import com.FinalExam.SirmaFinalExam.Models.Records;
import com.FinalExam.SirmaFinalExam.Services.RecordsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/records")
public class RecordsController {
    private final RecordsCSVReader recordsCSVReader;
    private final RecordsService recordsService;

    public RecordsController(RecordsCSVReader recordsCSVReader, RecordsService recordsService) {
        this.recordsCSVReader = recordsCSVReader;
        this.recordsService = recordsService;
    }

    @PostMapping("saveAll")
    public ResponseEntity saveAllRecords() {
        this.recordsService.saveAllRecords();
        return ResponseEntity.status(201).body("records are saved");
    }


    @PostMapping("deleteAll/")
    public ResponseEntity deleteAllRecords() {
        this.recordsService.deleteAllRecords();
        return ResponseEntity.ok("all records are deleted");
    }

    @GetMapping("/getAll")
    public List<Records> getAllRecords() {
        return this.recordsService.getRecords();
    }


}
