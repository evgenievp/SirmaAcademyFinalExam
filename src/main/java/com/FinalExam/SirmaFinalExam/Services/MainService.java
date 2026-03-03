package com.FinalExam.SirmaFinalExam.Services;

import com.FinalExam.SirmaFinalExam.Repos.MainRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    private final MainRepo mainRepo;


    public MainService(MainRepo mainRepo, MainRepo mainRepo1) {
        this.mainRepo = mainRepo1;
    }

    public List<Object[]> getMostTimePlayedPlayersPair() {
        return this.mainRepo.getPlayerPairsWithMostTime();
    }

    public Object[] justPair() {
        return this.mainRepo.pairOfPlayers();
    }

}
