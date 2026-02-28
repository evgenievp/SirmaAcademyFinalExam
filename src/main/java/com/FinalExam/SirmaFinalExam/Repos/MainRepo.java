package com.FinalExam.SirmaFinalExam.Repos;

import com.FinalExam.SirmaFinalExam.Models.Players;
import com.FinalExam.SirmaFinalExam.Models.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MainRepo extends JpaRepository<Records, Integer> {


    @Query(nativeQuery = true, value = """
            select * from players;
            """)
    List<Players> pairPlayedMostTime();
}
