package com.FinalExam.SirmaFinalExam.Services;

import com.FinalExam.SirmaFinalExam.Models.Players;
import com.FinalExam.SirmaFinalExam.Repos.MainRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    private final MainRepo mainRepo;


    public MainService(MainRepo mainRepo, MainRepo mainRepo1) {
        this.mainRepo = mainRepo1;
    }

    public List<Players> getMostTimePlayedPlayersPair() {
        return this.mainRepo.pairPlayedMostTime();
    }


}
